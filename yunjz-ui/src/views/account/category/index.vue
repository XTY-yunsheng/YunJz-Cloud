<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账单类型" prop="typeId">
        <el-select v-model="queryParams.typeId" filterable clearable placeholder="请选择" style="width: 140px;">
          <el-option
            v-for="item in typeNameVo"
            :key="item.typeId"
            :label="item.typeName"
            :value="item.typeId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="账单类别名称" prop="categoryName" label-width="100px">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入账单类别名称"
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
          v-hasPermi="['account:category:add']"
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
          v-hasPermi="['account:category:edit']"
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
          v-hasPermi="['account:category:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:category:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="categoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="categoryId" />
      <el-table-column label="账单类型" align="center" prop="typeName">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.typeName==null" type="danger" effect="plain">暂未关联类型</el-tag>
          <el-tag v-else-if="scope.row.typeName.includes('收入')" type="success" effect="plain">{{scope.row.typeName}}</el-tag>
          <el-tag v-else-if="scope.row.typeName.includes('支出')" type="warning" effect="plain">{{scope.row.typeName}}</el-tag>
          <el-tag v-else type="info" effect="plain">{{scope.row.typeName}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="账单类别名称" align="center" prop="categoryName" />
      <el-table-column label="启用状态" align="center" prop="showStatus">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.showStatus"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="0"
            :inactive-value="1"
            @change="updateCategoryStatus(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['account:category:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['account:category:remove']"
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

    <!-- 添加或修改账单类别对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类别名称" prop="categoryName">
          <el-input v-model="form.categoryName" placeholder="请输入账单类别名称" />
        </el-form-item>
        <el-form-item label="选择类型" prop="typeName">
          <el-select v-model="form.typeId" filterable clearable placeholder="请选择" style="width: 140px;">
            <el-option
              v-for="item in typeNameVo"
              :key="item.typeId"
              :label="item.typeName"
              :value="item.typeId">
            </el-option>
          </el-select>
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
  </div>
</template>

<script>
import { listCategory, getCategory, delCategory, addCategory, updateCategory, updateCategoryStatus } from "@/api/account/category";
import { getTypeNameList } from "@/api/account/type";

export default {
  name: "Category",
  data() {
    return {
      typeNameVo: [{label: "a", value: 1}],
      type: '',
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
      // 账单类别表格数据
      categoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeId: null,
        categoryName: null,
        showStatus: null,
        sort: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        categoryName: [{ required: true, message: "名称不能为空", trigger: "blur" }],
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
    this.getTypeNameList();
    //this.$emit("fil-type")
  },
  computed: {
    filType(row) {
      return this.typeNameVo.filter(t => t.value === row.typeId)
    }
  },
  methods: {
    updateCategoryStatus(data) {
      let { categoryId, showStatus } = data;
      //发送请求修改状态
      this.$modal.confirm('是否确认更改账单类别id为"' + categoryId + '"的数据项？').then(function() {
        return updateCategoryStatus({ categoryId, showStatus });
      }).then(({ data }) => {
        this.$message({
          type: "success",
          message: `账单类别id为 ${categoryId} 的状态更新成功`
        });
        // Book.methods.getUserNameList();
      }).catch(() =>{});
    },

    getTypeNameList() {
      getTypeNameList().then(({data}) => {
        console.log("optional_types_list: ", data);
        this.typeNameVo = data;
      });
    },

    /** 查询账单类别列表 */
    getList() {
      this.loading = true;
      listCategory(this.queryParams).then(response => {
        console.log("category_list: ", response.rows)
        this.categoryList = response.rows;
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
        categoryId: null,
        typeId: null,
        categoryName: null,
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
      this.ids = selection.map(item => item.categoryId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加账单类别";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const categoryId = row.categoryId || this.ids
      getCategory(categoryId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改账单类别";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.categoryId != null) {
            updateCategory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCategory(this.form).then(response => {
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
      const categoryIds = row.categoryId || this.ids;
      this.$modal.confirm('是否确认删除账单类别编号为"' + categoryIds + '"的数据项？').then(function() {
        return delCategory(categoryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('account/category/export', {
        ...this.queryParams
      }, `category_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
