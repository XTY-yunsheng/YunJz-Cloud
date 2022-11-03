package com.yunjz.account.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.yunjz.account.domain.TypeCategoryRelation;
import com.yunjz.account.service.ITypeCategoryRelationService;
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
@RequestMapping("/tc_relation")
public class TypeCategoryRelationController extends BaseController
{
    @Autowired
    private ITypeCategoryRelationService typeCategoryRelationService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("account:relation:list")
    @GetMapping("/list")
    public TableDataInfo list(TypeCategoryRelation typeCategoryRelation)
    {
        startPage();
        List<TypeCategoryRelation> list = typeCategoryRelationService.selectTypeCategoryRelationList(typeCategoryRelation);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("account:relation:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TypeCategoryRelation typeCategoryRelation)
    {
        List<TypeCategoryRelation> list = typeCategoryRelationService.selectTypeCategoryRelationList(typeCategoryRelation);
        ExcelUtil<TypeCategoryRelation> util = new ExcelUtil<TypeCategoryRelation>(TypeCategoryRelation.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("account:relation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(typeCategoryRelationService.selectTypeCategoryRelationById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("account:relation:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TypeCategoryRelation typeCategoryRelation)
    {
        return toAjax(typeCategoryRelationService.insertTypeCategoryRelation(typeCategoryRelation));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("account:relation:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TypeCategoryRelation typeCategoryRelation)
    {
        return toAjax(typeCategoryRelationService.updateTypeCategoryRelation(typeCategoryRelation));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("account:relation:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(typeCategoryRelationService.deleteTypeCategoryRelationByIds(ids));
    }
}
