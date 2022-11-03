<template>
  <div>
    <el-dialog :close-on-click-modal="false" :visible.sync="visible" @closed="dialogClose" title="关联账单类别">
      <el-dialog width="40%" title="选择类别" :visible.sync="innerVisible" append-to-body :close-on-click-modal="false">
        <div>
          <el-form :inline="true" :model="queryParams" @keyup.enter.native="getDataList()">
            <el-form-item>
              <el-input size="small" v-model="queryParams.categoryName" placeholder="参数名" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button size="small" icon="el-icon-search" @click="getDataList()">查询</el-button>
            </el-form-item>
          </el-form>
          <el-table :data="dataList" border v-loading="dataListLoading" @selection-change="innerSelectionChangeHandle" style="width: 100%;">
            <el-table-column type="selection" header-align="center" align="center"></el-table-column>
            <el-table-column prop="categoryId" header-align="center" align="center" label="类别id"></el-table-column>
            <el-table-column prop="categoryName" header-align="center" align="center" label="类别名"></el-table-column>
          </el-table>
          <pagination
            @pagination="getDataList"
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
          />
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="innerVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="submitAddRealtion">确认新增</el-button>
        </div>
      </el-dialog>
      <el-row>
        <el-col :span="24">
          <el-button size="mini" type="primary" @click="addRelation">新建关联</el-button>
          <el-button size="mini" type="danger" @click="batchDeleteRelation" :disabled="dataListSelections.length <= 0">批量删除</el-button>
          <div style="height: 5px"></div>
          <el-table :data="relationCategorys" style="width: 100%" @selection-change="selectionChangeHandle" border>
            <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
            <el-table-column prop="categoryId" label="类别id"></el-table-column>
            <el-table-column prop="categoryName" label="类类别名"></el-table-column>
            <el-table-column  header-align="center" align="center" label="操作">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="relationRemove(scope.row.categoryId)">移除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" type="primary" @click="visible = false" >关闭窗口</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from '《组件路径》';
import {listRelation, delRelation, listNoRelationCategory, addRelation} from "@/api/account/tc_relation";
export default {
  //import引入的组件需要注入到对象中才能使用
  components: {},
  props: {},
  data() {
    //这里存放数据
    return {
      typeId: 0,
      visible: false,
      innerVisible: false,
      relationCategorys: [],
      dataListSelections: [],
      dataForm: {
        key: ""
      },
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeId: null,
        categoryName: null,
        showStatus: null,
        sort: null
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      innerdataListSelections: []
    };
  },
  //计算属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    selectionChangeHandle(val) {
      // console.log("val: ",val)
      this.dataListSelections = val;
    },
    innerSelectionChangeHandle(val) {
      this.innerdataListSelections = val;
    },
    addRelation() {
      this.getDataList();
      this.innerVisible = true;
    },
    batchDeleteRelation(val) {
      let postData = [];
      this.dataListSelections.forEach(item => {
        postData.push({ categoryId: item.categoryId, categoryName: item.categoryName, typeId: this.typeId });
      });
      let ids = [];
      postData.forEach((e, i, s) =>ids.push(e.categoryId));
      this.$modal.confirm('是否确认删除账单类别编号为[' + ids + ']的关联关系数据项？').then(function() {
        return delRelation(postData);
      }).then(( data ) => {
        if (data.code === 200) {
          this.$message({ type: "success", message: "删除成功" });
          this.init(this.typeId);
        } else {
          this.$message({ type: "error", message: data.msg });
        }
      }).catch(() => {});
    },
    //移除关联
    relationRemove(categoryId) {
      let data = [];
      data.push({ categoryId, typeId: this.typeId });
      this.$modal.confirm('是否确认删除账单类别编号为"' + categoryId + '"的关联关系数据项？').then(function() {
        return delRelation(data);
      }).then(( data ) => {
        if (data.code === 200) {
          this.$message({ type: "success", message: "删除成功" });
          this.init(this.typeId);
        } else {
          this.$message({ type: "error", message: data.msg });
        }
      }).catch(() => {});
    },
    submitAddRealtion() {
      this.innerVisible = false;
      //准备数据
      console.log("准备新增的数据", this.innerdataListSelections);
      if (this.innerdataListSelections.length > 0) {
        let postData = [];
        this.innerdataListSelections.forEach(item => {
          postData.push({ categoryId: item.categoryId, typeId: this.typeId });
        });
        addRelation(postData).then(( data ) => {
          if (data.code === 200) {
            this.$message({ type: "success", message: "新增关联成功" });
          }
          this.init(this.typeId);
          this.$emit("refreshData");
        });
      } else {
      }
    },
    init(typeId) {
      this.typeId = typeId || 0;
      this.visible = true;
      listRelation(this.typeId).then(( data ) => {
        console.log("relation_list_data: ", data)
        this.relationCategorys = data.data;
      });
    },
    dialogClose() {},

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      listNoRelationCategory(this.queryParams, this.typeId).then(( data ) => {
        console.log("nocategory_list_data= ",data)
        if (data && data.code === 200) {
          this.dataList = data.rows;
          this.total = data.total;
        } else {
          this.dataList = [];
        }
        this.dataListLoading = false;
      });
    },
  }
};
</script>
<style>
</style>
