package com.yunjz.account.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.yunjz.account.vo.CategoryNameVo;
import com.yunjz.account.vo.CategoryRespVo;
import com.yunjz.common.core.myvalid.AddGroup;
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
import com.yunjz.account.domain.AccCategory;
import com.yunjz.account.service.IAccCategoryService;
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
@RequestMapping("/category")
public class AccCategoryController extends BaseController
{
    @Autowired
    private IAccCategoryService accCategoryService;

    /**
     * 查询账单类别列表
     */
    @RequiresPermissions("account:category:list")
    @GetMapping("/list")
    public TableDataInfo list(AccCategory accCategory)
    {
        startPage();
        List<CategoryRespVo> list = accCategoryService.selectAccCategoryList(accCategory);
        return getDataTable(list);
    }

    /**
     * 根据typeId获取类别名称列表     /account/category/categorynamelist/{typeId}
     */
    @GetMapping("/categorynamelist/{typeId}")
    public AjaxResult listName(@PathVariable(value = "typeId", required = false) Long typeId)
    {
        List<CategoryNameVo> list = accCategoryService.getCategoryNameListByTypeId(typeId);
        return AjaxResult.success(list);
    }

    /**
     * 导出账单列表列表
     */
    @RequiresPermissions("account:category:export")
    @Log(title = "导出账单列表列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AccCategory accCategory)
    {
        List<CategoryRespVo> list = accCategoryService.selectAccCategoryList(accCategory);
        ExcelUtil<CategoryRespVo> util = new ExcelUtil<CategoryRespVo>(CategoryRespVo.class);
        util.exportExcel(response, list, "导出账单列表列表数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("account:category:query")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return AjaxResult.success(accCategoryService.selectAccCategoryByCategoryId(categoryId));
    }

    /**
     * 新增账单类别，并添加关联关系
     */
    @RequiresPermissions("account:category:add")
    @Log(title = "新增账单类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody AccCategory accCategory)
    {
        accCategoryService.addCategoryWithRelation(accCategory);
        return AjaxResult.success();
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("account:category:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(AddGroup.class) @RequestBody AccCategory accCategory)
    {
        return toAjax(accCategoryService.updateAccCategory(accCategory));
    }

    /**
     * 修改账单类别状态     /account/category/status
     */
    @RequiresPermissions("account:category:edit")
    @Log(title = "修改账单类别状态", businessType = BusinessType.UPDATE)
    @PostMapping("/status")
    public AjaxResult editStatus(@RequestBody AccCategory accCategory)
    {
        accCategoryService.updateAccCategory(accCategory);
        return AjaxResult.success();
    }

    /**
     * 删除账单类别，和关联关系
     */
    @RequiresPermissions("account:category:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        accCategoryService.deleteCategoryWithRelationByCategoryIds(categoryIds);
        return AjaxResult.success();
    }
}
