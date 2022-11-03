<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="账单类型名称" prop="typeName">
        <el-input
          v-model="queryParams.typeName"
          placeholder="请输入账单类型名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['account:type:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['account:type:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['account:type:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:type:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="typeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="typeId" />
      <el-table-column label="账单类型名称" align="center" prop="typeName" />
      <el-table-column label="状态" align="center" prop="showStatus">
        <!--<template slot-scope="scope">
          <i class="el-icon-success" v-if="scope.row.showStatus===0"></i>
          <i class="el-icon-error" v-else></i>
        </template>-->
        <template slot-scope="scope">
          <el-tag v-if="scope.row.showStatus===0">正常</el-tag>
          <el-tag v-else type="danger">禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="180">
        <template slot-scope="scope">
          <el-button type="text" size="mini" icon="el-icon-link" @click="relationHandle(scope.row.typeId)" style="width: 50px;border: limegreen 1px solid;color: limegreen"
          >关联</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['account:type:edit']"
          >修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['account:type:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改账单类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型名称" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入账单类型名称" />
        </el-form-item>
        <el-form-item label="状态" prop="showStatus">
          <el-switch v-model="form.showStatus" style="margin-left: 10px"
                     active-text="启用" inactive-text="禁用"
                     active-color="#13ce66" inactive-color="#ff4949"
                     :inactive-value="1" :active-value="0"
          ></el-switch>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model.number="form.sort" placeholder="请输入排序" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改关联关系 -->
    <relation-update v-if="relationVisible" ref="relationUpdate" @refreshData="getList"></relation-update>

  </div>
</template>

<script>
import { listType, getType, delType, addType, updateType } from "@/api/account/type";
import RelationUpdate from "./relation-update";

export default {
  components: {RelationUpdate},
  name: "Type",
  data() {
    return {
      relationVisible: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 账单类型表格数据
      typeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeName: null,
        showStatus: null,
        sort: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        typeName: [{ required: true, message: "名称不能为空", trigger: "blur" }],
        sort: [{
          validator: (rule, value, callback) => {
            if (value === "" || value === null) {
              callback(new Error("排序字段必须填写"));
            } else if (!Number.isInteger(value) || value<0) {
              callback(new Error("排序必须是一个大于等于0的整数"));
            } else {
              callback();
            }
          },
          trigger: "blur"
        }],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    relationHandle(typeId) {
      console.log("click_typeId: ", typeId)
      this.relationVisible = true;
      this.$nextTick(() => {
        this.$refs.relationUpdate.init(typeId);
      });
    },

    /** 查询账单类型列表 */
    getList() {
      this.loading = true;
      listType(this.queryParams).then(response => {
        console.log("type_list: ", response.rows)
        this.typeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        typeId: null,
        typeName: null,
        showStatus: 0,
        sort: 0,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.typeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加账单类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const typeId = row.typeId || this.ids
      getType(typeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改账单类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.typeId != null) {
            updateType(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addType(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const typeIds = row.typeId || this.ids;
      this.$modal.confirm('是否确认删除账单类型编号为"' + typeIds + '"的数据项？').then(function() {
        return delType(typeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('account/type/export', {
        ...this.queryParams
      }, `type_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
