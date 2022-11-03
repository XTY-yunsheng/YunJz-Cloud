<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账本名称" prop="bookName">
        <el-input style="width: 140px;"
          v-model="queryParams.bookName"
          placeholder="请输入账本名称"
          clearable
          @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="用户名称" prop="bookName">
        <el-input style="width: 140px;"
                  v-model="queryParams.userName"
                  placeholder="请输入用户名称"
                  clearable/>
      </el-form-item>
      <el-form-item label="开始时间" prop="gmtCreate" style="margin-left: 30px">
        <el-date-picker clearable style="width: 140px;"
          v-model="queryParams.gmtCreate.min"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="gmtCreate">
        <el-date-picker clearable style="width: 140px;"
          v-model="queryParams.gmtCreate.max"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束时间">
        </el-date-picker>
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
          v-hasPermi="['account:book:add']"
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
          v-hasPermi="['account:book:edit']"
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
          v-hasPermi="['account:book:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:book:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="bookId" />
      <el-table-column label="账本名称" align="center" prop="bookName" />
      <el-table-column label="所属用户" align="center" prop="userName" />
      <el-table-column label="默认账本" align="center" prop="defaultStatus">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.defaultStatus===0" type="success">是</el-tag>
          <el-tag v-else>否</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="启用状态" align="center" prop="showStatus">
        <template slot-scope="scope">
          <i class="el-icon-success" v-if="scope.row.showStatus===0"></i>
          <i class="el-icon-error" v-else></i>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180">
        <!--<template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>
        </template>-->
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="gmtModified" width="180">
        <!--<template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d}') }}</span>
        </template>-->
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['account:book:edit']"
          >修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['account:book:remove']"
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

    <!-- 添加或修改账本对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账本名称" prop="bookName">
          <el-input v-model="form.bookName" placeholder="请输入账本名称" />
        </el-form-item>
        <el-form-item label="选择用户" prop="userId" v-if="this.title === '添加账本'">
          <el-select v-model="form.userId" filterable clearable placeholder="请选择用户" style="width: 140px;">
            <el-option
              v-for="item in userNameVo"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="默认账本" prop="defaultStatus">
          <el-switch v-model="form.defaultStatus" style="margin-left: 14px"
                     active-text="是" inactive-text="否"
                     active-color="#13ce66" inactive-color="#ff4949"
                     :inactive-value="1" :active-value="0"
          ></el-switch>
        </el-form-item>
        <!--<el-form-item label="启用状态" prop="showStatus">
          <el-switch v-model="form.showStatus"
                     active-text="启用" inactive-text="禁用"
                     active-color="#13ce66" inactive-color="#ff4949"
                     :inactive-value="1" :active-value="0"
          ></el-switch>
        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBook, getBook, delBook, addBook, updateBook } from "@/api/account/book";
import {getUserNameList} from "@/api/account/ub_relation"
import {getUserList} from "@/api/member/user"
export default {
  name: "Book",
  data() {
    return {
      userNameVo: [{label: "a", value: 1}],
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
      // 账本表格数据
      bookList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bookName: null,
        userName: null,
        defaultStatus: null,
        showStatus: null,
        deleted: null,
        gmtCreate: {
          min: null,
          max: null
        },
        gmtModified: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bookName: [{ required: true, message: "账本名称不能为空", trigger: "blur" }],
        userId: [{ required: true, message: "请选择用户", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getList();
    this.getUserNameList();
  },
  methods: {
    getUserNameList() {
      getUserList().then(({data}) => {
        console.log("optional_users_list: ", data);
        this.userNameVo = data;
      });
    },

    /** 查询账本列表 */
    getList() {
      this.loading = true;
      listBook(this.queryParams).then(response => {
        console.log("book_list: ", response.rows);
        this.bookList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      //this.getUserNameList();
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        bookId: null,
        bookName: null,
        userId: null,
        defaultStatus: 1,
        showStatus: 0,
        deleted: null,
        gmtCreate: null,
        gmtModified: null
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
      this.ids = selection.map(item => item.bookId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加账本";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bookId = row.bookId || this.ids
      getBook(bookId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改账本";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bookId != null) {
            updateBook(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBook(this.form).then(response => {
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
      const bookIds = row.bookId || this.ids;
      this.$modal.confirm('是否确认删除账本编号为"' + bookIds + '"的数据项？').then(function() {
        return delBook(bookIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('account/book/export', {
        ...this.queryParams
      }, `book_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
