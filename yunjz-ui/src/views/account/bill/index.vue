<template>
  <div class="app-container">
    <!--查询框-->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="账单标题" prop="title">
        <el-input style="width: 180px;"
          v-model="queryParams.title"
          placeholder="请输入账单标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属用户" prop="userId">
        <el-select v-model="queryParams.userId" filterable clearable placeholder="请选择用户" style="width: 180px;" @change="userChange(queryParams.userId)">
          <el-option
            v-for="item in userNameVo"
            :key="item.userId"
            :label="item.userName"
            :value="item.userId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属账本" prop="bookId">
        <el-select v-model="queryParams.bookId" filterable clearable placeholder="请选择账本" style="width: 180px;">
          <el-option
            v-for="item in bookNameVo"
            :key="item.bookId"
            :label="item.bookName"
            :value="item.bookId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类型" prop="typeId">
        <el-select v-model="queryParams.typeId" filterable clearable placeholder="请选择类型" style="width: 140px;" @change="typeChange(queryParams.typeId)">
          <el-option
            v-for="item in typeNameVo"
            :key="item.typeId"
            :label="item.typeName"
            :value="item.typeId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="类别" prop="categoryId" label-width="45px">
        <el-select v-model="queryParams.categoryId" filterable clearable placeholder="请选择类别" style="width: 140px;">
          <el-option
            v-for="item in categoryNameVo"
            :key="item.categoryId"
            :label="item.categoryName"
            :value="item.categoryId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="gmtCreate">
        <el-date-picker clearable style="width: 180px;"
          v-model="queryParams.gmtCreate.min"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="gmtModified">
        <el-date-picker clearable style="width: 180px;"
          v-model="queryParams.gmtCreate.max"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item style="margin-left: 5px">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!--按钮栏-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['account:bill:add']"
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
          v-hasPermi="['account:bill:edit']"
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
          v-hasPermi="['account:bill:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:bill:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="billList.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))" @selection-change="handleSelectionChange" border
              :default-sort = "{prop: 'id', order: 'descending'}" size="medium" :show-summary="true" :summary-method="getSummaries">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="billId" />
      <el-table-column label="账单号" align="center" prop="orderSn" width="180" />
      <el-table-column label="账单标题" align="center" prop="title" width="200">
        <template slot="header" slot-scope="scope"> 账单标题 &nbsp;
          <el-input style="width: 120px"
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"/>
        </template>
      </el-table-column>
      <el-table-column label="所属用户" align="center" prop="userName" />
      <el-table-column label="所属账本" align="center" prop="bookName" width="160"/>
      <el-table-column label="类型" align="center" prop="typeName" />
      <el-table-column label="类别" align="center" prop="categoryName" />
      <el-table-column label="金额" align="center" prop="money" sortable width="90"/>
      <el-table-column label="创建时间" align="center" prop="gmtCreate" sortable width="160">
        <!--<template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtCreate, '{y}-{m}-{d}') }}</span>
        </template>-->
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="gmtModified" sortable width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.gmtModified, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['account:bill:edit']"
          >修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['account:bill:remove']"
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

    <!-- 添加或修改账单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body @close="CloseDialog" :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账单标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入账单标题" />
        </el-form-item>
        <el-form-item label="选择用户" prop="userId">
          <el-select v-model="form.userId" filterable placeholder="请选择用户" style="width: 180px;" @change="userChange(form.userId)" :disabled="form.billId !== null">
            <el-option
              v-for="item in userNameVo"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择账本" prop="bookId">
          <el-select v-model="form.bookId" filterable clearable placeholder="请选择账本" style="width: 180px;">
            <el-option
              v-for="item in bookNameVo"
              :key="item.bookId"
              :label="item.bookName"
              :value="item.bookId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类型" prop="typeId">
          <el-select v-model="form.typeId" filterable placeholder="请选择类型" style="width: 180px;" @change="typeChange(form.typeId)">
            <el-option
              v-for="item in typeNameVo"
              :key="item.typeId"
              :label="item.typeName"
              :value="item.typeId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="类别" prop="categoryId">
          <el-select v-model="form.categoryId" filterable clearable placeholder="请选择类别" style="width: 180px;">
            <el-option
              v-for="item in categoryNameVo"
              :key="item.categoryId"
              :label="item.categoryName"
              :value="item.categoryId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="金额" prop="money">
          <el-input v-model="form.money" placeholder="请输入金额" />
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
import { listBill, getBill, delBill, addBill, updateBill } from "@/api/account/bill";
import {getUserNameList} from "@/api/account/ub_relation"
import {getUserList} from "@/api/member/user"
import {getTypeNameList} from "@/api/account/type"
import {getCategoryNameListByTypeId} from "@/api/account/category"
import {getBookNameListByUserId} from "@/api/account/book"

export default {
  name: "Bill",
  data() {
    return {
      userNameVo: [{label: "a", value: 1}],
      bookNameVo: [{label: "a", value: 1}],
      typeNameVo: [{label: "a", value: 1}],
      categoryNameVo: [{label: "a", value: 1}],
      search: '',
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
      // 账单列表表格数据
      billList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderSn: null,
        title: null,
        userId: null,
        userName: null,
        bookId: null,
        typeId: null,
        categoryId: null,
        money: null,
        deleted: null,
        gmtCreate: {
          min: null,
          max: null
        },
        gmtModified: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [{ required: true, message: "订单标题不能为空", trigger: "blur" }],
        userId: [{ required: true, message: "请选择用户", trigger: "blur" }],
        bookId: [{ required: true, message: "请选择账本", trigger: "blur" }],
        typeId: [{ required: true, message: "请选择类型", trigger: "blur" }],
        categoryId: [{ required: true, message: "请选择类别", trigger: "blur" }],
        money: [
          {
            validator: (rule, value, callback) => {
              if (value === "" || value === null) {
                callback(new Error("金额不能为空"));
              }
              let reg = /^\d+(\.?\d{1,2})?$/;
              if (!reg.test(value)) {
                callback(new Error("金额格式不正确, 且最多两位小数"));
              } else {
                callback();
              }
            },
            trigger: "blur"
          }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getUserNameList();
    this.getTypeNameList();
  },
  methods: {
    getUserNameList() {
      getUserList().then(({data}) => {
        console.log("optional_users_list: ", data);
        this.userNameVo = data;
      });
    },
    userChange(userId) {
      userId = userId || 0;
      getBookNameListByUserId(userId).then(({data}) => {
        console.log("optional_books_list: ", data);
        this.bookNameVo = data;
      }).catch(() => {
      });
    },
    typeChange(typeId) {
      typeId = typeId || 0;
      // console.log("typeId: ", typeId);
      getCategoryNameListByTypeId(typeId).then(({data}) => {
        console.log("optional_categorys_list: ", data);
        this.categoryNameVo = data;
      }).catch(() =>{});
    },
    getTypeNameList() {
      getTypeNameList().then(({data}) => {
        console.log("optional_types_list: ", data);
        this.typeNameVo = data;
      });
    },
    CloseDialog() {
      this.bookNameVo = [];
      this.categoryNameVo = [];
    },

    /** 查询账单列表列表 */
    getList() {
      this.loading = true;
      listBill(this.queryParams).then(response => {
        this.billList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 表格合计 */
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '合计';
          return;
        } else {
          sums[index] = '';
        }
        const values = data.map(item => Number(item[column.property]));
        if (!values.every(value => isNaN(value)) && index === 8) {
          sums[8] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);
          sums[8] += ' 元';
        }
        sums[columns.length-1] = data.length + ' 条记录';
      });
      return sums;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        billId: null,
        orderSn: null,
        title: null,
        userId: null,
        bookId: null,
        typeId: null,
        categoryId: null,
        money: null,
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
      this.queryParams.gmtCreate = {};
      this.bookNameVo = [];
      this.categoryNameVo = [];
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.billId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加账单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const billId = row.billId || this.ids
      getBill(billId).then(response => {
        this.form = response.data;
        this.userChange(row.userId);
        this.typeChange(this.form.typeId);
        this.open = true;
        this.title = "修改账单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.billId != null) {
            updateBill(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBill(this.form).then(response => {
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
      const billIds = row.billId || this.ids;
      this.$modal.confirm('是否确认删除账单编号为"' + billIds + '"的数据项？').then(function() {
        return delBill(billIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('account/bill/export', {
        ...this.queryParams
      }, `bill_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
