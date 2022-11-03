package com.yunjz.account.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.yunjz.account.vo.UserNameVo;
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
import com.yunjz.account.domain.UserBookRelation;
import com.yunjz.account.service.IUserBookRelationService;
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
@RequestMapping("/ub_relation")
public class UserBookRelationController extends BaseController
{
    @Autowired
    private IUserBookRelationService userBookRelationService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("account:relation:list")
    @GetMapping("/list")
    public TableDataInfo list(UserBookRelation userBookRelation)
    {
        startPage();
        List<UserBookRelation> list = userBookRelationService.selectUserBookRelationList(userBookRelation);
        return getDataTable(list);
    }

    /**
     * 查询可用用户名称列表    /account/ub_relation/list/username
     */
    @GetMapping("/list/username")
    public AjaxResult listUserName()
    {
        List<UserNameVo> list = userBookRelationService.getUserNameList();
        return AjaxResult.success(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("account:relation:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserBookRelation userBookRelation)
    {
        List<UserBookRelation> list = userBookRelationService.selectUserBookRelationList(userBookRelation);
        ExcelUtil<UserBookRelation> util = new ExcelUtil<UserBookRelation>(UserBookRelation.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("account:relation:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userBookRelationService.selectUserBookRelationById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("account:relation:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserBookRelation userBookRelation)
    {
        return toAjax(userBookRelationService.insertUserBookRelation(userBookRelation));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("account:relation:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserBookRelation userBookRelation)
    {
        return toAjax(userBookRelationService.updateUserBookRelation(userBookRelation));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("account:relation:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userBookRelationService.deleteUserBookRelationByIds(ids));
    }
}
