<template>
  <div class="app-container">
    <!--查询栏-->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="userName">
        <el-input style="width: 140px;"
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员等级" prop="levelId">
        <el-select v-model="queryParams.levelId" filterable clearable placeholder="请选择" style="width: 140px;">
          <el-option
            v-for="item in levelVo"
            :key="item.levelId"
            :label="item.name"
            :value="item.levelId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="gmtCreate" style="margin-left: 30px">
        <el-date-picker clearable  style="width: 140px;"
          v-model="queryParams.gmtCreate.min"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="gmtModified">
        <el-date-picker clearable  style="width: 140px;"
          v-model="queryParams.gmtCreate.max"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item style="margin-left: 10px">
        <el-button type="primary" icon="el-icon-search" size="small" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="small" @click="resetQuery">重置</el-button>
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
          v-hasPermi="['account:user:add']"
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
          v-hasPermi="['account:user:edit']"
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
          v-hasPermi="['account:user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:user:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!--表格-->
    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="userId" />
      <el-table-column label="用户名" align="center" prop="userName" width="120"/>
      <el-table-column label="手机号" align="center" prop="mobile" width="120" />
      <el-table-column label="会员等级" align="center" prop="levelName" width="100">
        <template slot-scope="scope">
          <el-tag effect="plain">{{scope.row.levelName}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="积分值" align="center" prop="integration" width="90" />
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.gender===0">男</el-tag>
          <el-tag v-else type="success">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="生日" align="center" prop="birth" width="180">
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 6px">{{ parseTime(scope.row.birth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="启用状态" align="center" prop="showStatus">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.showStatus"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="0"
            :inactive-value="1"
            @change="updateUserStatus(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="gmtCreate" width="180" />
      <el-table-column label="更新时间" align="center" prop="gmtModified" width="180" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['account:user:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['account:user:remove']"
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

    <!-- 添加或修改 用户 对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body :close-on-click-modal="false">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="积分值" prop="integration">
          <el-input v-model="form.integration" placeholder="请输入积分值" />
        </el-form-item>
        <el-form-item label="密码" prop="password" v-if="form.userId != null">
          <el-input v-model="form.password" type="password"  placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="form.gender">
            <el-radio :label="0">男</el-radio>
            <el-radio :label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日" prop="birth">
          <el-date-picker clearable
            v-model="form.birth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择生日">
          </el-date-picker>
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
import { listUser, getUser, delUser, addUser, updateUser, updateUserStatus } from "@/api/member/user";
import { getLevelList } from "@/api/member/level";
import Book from "../../member/user/index.vue";

export default {
  components: {Book},
  name: "User",
  data() {
    return {
      levelVo: [{label: "a", value: 1}],
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
      // 【请填写功能名称】表格数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        password: null,
        openid: null,
        mobile: null,
        nickname: null,
        levelId: null,
        integration: null,
        gender: null,
        birth: null,
        sign: null,
        showStatus: null,
        deleted: null,
        gmtCreate: {
          min: null,
          max: null
        },
        gmtModified: null,
        socialUid: null,
        accessToken: null,
        expiresIn: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userName: [{ required: true, message: "用户名不能为空", trigger: "blur" },{ min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }],
        password: [{ required: true, message: "密码不能为空", trigger: "blur" },{ min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }],
        integration: [{ required: true, message: "积分值不能为空", trigger: "blur" }],
        birth: [{ required: true, message: "生日不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
    this.getLevelNameList();
  },
  methods: {
    updateUserStatus(row) {
      // console.log("最新信息", row);
      let { userId, showStatus } = row;
      //发送请求修改状态
      this.$modal.confirm('是否确认更改用户id为"' + userId + '"的数据项？').then(function() {
        return updateUserStatus({ userId, showStatus });
      }).then(({ data }) => {
        this.$message({
          type: "success",
          message: `用户id为 ${userId} 的状态更新成功`
        });
        // Book.methods.getUserNameList();
      }).catch(() => {row.showStatus = row.showStatus === "0" ? "1" : "0";});
    },
    getLevelNameList() {
      getLevelList().then(({data}) => {
        console.log("optional_levels_list: ", data);
        this.levelVo = data;
      });
    },

    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUser(this.queryParams).then(response => {
        console.log("user_list: ", response.rows)
        this.userList = response.rows;
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
        userId: null,
        userName: null,
        password: 123456,
        openid: null,
        mobile: null,
        nickname: null,
        levelId: null,
        integration: 0,
        gender: 0,
        birth: null,
        sign: null,
        showStatus: 0,
        deleted: null,
        gmtCreate: null,
        gmtModified: null,
        socialUid: null,
        accessToken: null,
        expiresIn: null
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
      this.ids = selection.map(item => item.userId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户弹框";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids
      getUser(userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户弹框";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId != null) {
            updateUser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then(response => {
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
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除用户编号为"' + userIds + '"的数据项？').then(function() {
        return delUser(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('member/user/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
