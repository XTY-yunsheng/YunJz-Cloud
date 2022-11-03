package com.yunjz.account.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.yunjz.account.domain.AccCategory;
import com.yunjz.account.service.IAccCategoryService;
import com.yunjz.account.service.ITypeCategoryRelationService;
import com.yunjz.account.vo.TypeCatRelationVo;
import com.yunjz.account.vo.TypeNameVo;
import com.yunjz.common.core.myvalid.AddGroup;
import com.yunjz.common.core.myvalid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yunjz.common.log.annotation.Log;
import com.yunjz.common.log.enums.BusinessType;
import com.yunjz.common.security.annotation.RequiresPermissions;
import com.yunjz.account.domain.AccType;
import com.yunjz.account.service.IAccTypeService;
import com.yunjz.common.core.web.controller.BaseController;
import com.yunjz.common.core.web.domain.AjaxResult;
import com.yunjz.common.core.utils.poi.ExcelUtil;
import com.yunjz.common.core.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@RestController
@RequestMapping("/type")
public class AccTypeController extends BaseController
{
    @Autowired
    private IAccTypeService accTypeService;

    @Autowired
    IAccCategoryService accCategoryService;

    @Autowired
    ITypeCategoryRelationService typeCategoryRelationService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("account:type:list")
    @GetMapping("/list")
    public TableDataInfo list(AccType accType)
    {
        startPage();
        List<AccType> list = accTypeService.selectAccTypeList(accType);
        return getDataTable(list);
    }

    /**
     * 查询账单类型名称列表   /account/type/list/name
     */
    @RequiresPermissions("account:type:list")
    @GetMapping("/list/name")
    public AjaxResult listName(AccType accType)
    {
        List<TypeNameVo> list = accTypeService.getTypeList(accType);
        return AjaxResult.success(list);
    }

    /**
     * 根据typeId查询关联类别列表 /account/type/${typeId}/relation/list
     */
    @GetMapping("/{typeId}/relation/list")
    public AjaxResult categoryRelation(@PathVariable("typeId") Long typeId) {
        List<AccCategory> accCategorys = accCategoryService.getRelationCategroy(typeId);
        return AjaxResult.success().put("data", accCategorys);
    }

    /**
     * 根据typeId查询可以关联的类别列表 /account/type/${typeId}/no_relation/list
     */
    @GetMapping("/{typeId}/no_relation/list")
    public TableDataInfo categoryNoRelation(AccCategory accCategory, @PathVariable("typeId") Long typeId) {
        startPage();
        List<AccCategory> accCategorys = accCategoryService.getNoRelationCategroy(accCategory, typeId);
        return getDataTable(accCategorys);
    }

    /**
     * 新增关联关系   /account/type/add/relation
     */
    @PostMapping("/add/relation")
    public AjaxResult addRelation(@RequestBody List<TypeCatRelationVo> vos) {
        typeCategoryRelationService.saveBatchRelation(vos);
        return AjaxResult.success();
    }

    /**
     * 删除类型&类别关联关系  /account/type/relation/delete
     */
    @PostMapping("/relation/delete")
    public AjaxResult deleteRelation(@RequestBody TypeCatRelationVo[] vos) {
        typeCategoryRelationService.deleteRelation(vos);
        return AjaxResult.success();
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("account:type:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AccType accType)
    {
        List<AccType> list = accTypeService.selectAccTypeList(accType);
        ExcelUtil<AccType> util = new ExcelUtil<AccType>(AccType.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("account:type:query")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return AjaxResult.success(accTypeService.selectAccTypeByTypeId(typeId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("account:type:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody AccType accType)
    {
        return toAjax(accTypeService.insertAccType(accType));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("account:type:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(UpdateGroup.class) @RequestBody AccType accType)
    {
        return toAjax(accTypeService.updateAccType(accType));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("account:type:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(accTypeService.deleteAccTypeByTypeIds(typeIds));
    }
}
