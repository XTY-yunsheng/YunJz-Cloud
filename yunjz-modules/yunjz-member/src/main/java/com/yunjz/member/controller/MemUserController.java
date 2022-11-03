package com.yunjz.member.controller;

import com.yunjz.common.core.myvalid.AddGroup;
import com.yunjz.common.core.myvalid.UpdateGroup;
import com.yunjz.common.core.utils.poi.ExcelUtil;
import com.yunjz.common.core.web.controller.BaseController;
import com.yunjz.common.core.web.domain.AjaxResult;
import com.yunjz.common.core.web.page.TableDataInfo;
import com.yunjz.common.log.annotation.Log;
import com.yunjz.common.log.enums.BusinessType;
import com.yunjz.common.security.annotation.RequiresPermissions;
import com.yunjz.member.domain.MemUser;
import com.yunjz.member.service.IMemUserService;
import com.yunjz.member.vo.UserIdTo;
import com.yunjz.member.vo.UserNameVo;
import com.yunjz.member.vo.UserRespVo;
import com.yunjz.member.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 【用户】Controller
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@RestController
@RequestMapping("/user")
public class MemUserController extends BaseController
{
    @Autowired
    private IMemUserService memUserService;

    /**
     * 查询【用户】列表
     */
    @RequiresPermissions("member:user:list")
    @GetMapping("/list")
    public TableDataInfo list(UserVo userVo)
    {
        startPage();
        List<UserRespVo> list = memUserService.selectMemUserList(userVo);
        return getDataTable(list);
    }

    /**
     * 查询可用用户名称列表
     */
    @RequiresPermissions("member:user:list")
    @GetMapping("/list/name")
    public AjaxResult listName()
    {
        List<UserNameVo> list = memUserService.getUserNameList();
        return AjaxResult.success(list);
    }

    /**
     * 查询可用用户ids列表信息(远程)
     */
    @RequiresPermissions("member:user:list")
    @PostMapping("/list/feign")
    public AjaxResult feignUserList(@RequestBody UserIdTo userIdTo)
    {
        List<Long> list = memUserService.feignUserList(userIdTo);
        System.out.println("list===: " + list);
        return AjaxResult.success().put("data",list);
    }

    /**
     * 根据userId获取userName(远程)
     */
    @RequiresPermissions("member:user:query")
    @PostMapping(value = "/getNameById")
    public AjaxResult feignGetUserNameById(@RequestBody UserIdTo userIdTo)
    {
        System.out.println("userIdTo: "+userIdTo.toString());
        MemUser memUser = memUserService.selectMemUserByUserId(userIdTo.getUserId());
        String userName = memUser.getUserName();
        return AjaxResult.success().put("userName",userName);
    }


    /**
     * 导出【用户】列表
     */
    @RequiresPermissions("member:user:export")
    @Log(title = "导出【用户】列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserVo userVo)
    {
        List<UserRespVo> list = memUserService.selectMemUserList(userVo);
        ExcelUtil<UserRespVo> util = new ExcelUtil<UserRespVo>(UserRespVo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【用户】详细信息
     */
    @RequiresPermissions("member:user:query")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return AjaxResult.success(memUserService.selectMemUserByUserId(userId));
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("member:user:add")
    @Log(title = "新增【用户】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody MemUser memUser)
    {
        memUser.setGmtCreate(new Date());
        memUser.setGmtModified(new Date());
        return toAjax(memUserService.insertMemUser(memUser));
    }

    /**
     * 修改用户
     */
    @RequiresPermissions("member:user:edit")
    @Log(title = "【修改用户】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(UpdateGroup.class) @RequestBody MemUser memUser)
    {
        memUser.setGmtModified(new Date());
        return toAjax(memUserService.updateMemUser(memUser));
    }

    /**
     * 修改用户状态
     */
    @RequiresPermissions("member:user:edit")
    @Log(title = "修改用户状态", businessType = BusinessType.UPDATE)
    @PostMapping("status")
    public AjaxResult editSuatus(@RequestBody MemUser memUser)
    {

        memUser.setGmtModified(new Date());
        memUserService.updateMemUserStatus(memUser);
        return AjaxResult.success();
    }

    /**
     * 删除【用户】
     */
    @RequiresPermissions("member:user:remove")
    @Log(title = "删除【用户】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        //return toAjax(memUserService.deleteMemUserByUserIds(userIds));
        return toAjax(memUserService.removeByIds(Arrays.asList(userIds)));
    }
}
