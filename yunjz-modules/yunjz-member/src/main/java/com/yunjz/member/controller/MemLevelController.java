package com.yunjz.member.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.yunjz.common.core.myvalid.AddGroup;
import com.yunjz.common.core.myvalid.UpdateGroup;
import com.yunjz.member.vo.LevelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.yunjz.common.log.annotation.Log;
import com.yunjz.common.log.enums.BusinessType;
import com.yunjz.common.security.annotation.RequiresPermissions;
import com.yunjz.member.domain.MemLevel;
import com.yunjz.member.service.IMemLevelService;
import com.yunjz.common.core.web.controller.BaseController;
import com.yunjz.common.core.web.domain.AjaxResult;
import com.yunjz.common.core.utils.poi.ExcelUtil;
import com.yunjz.common.core.web.page.TableDataInfo;

/**
 * 【会员等级】Controller
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@RestController
@RequestMapping("/level")
public class MemLevelController extends BaseController
{
    @Autowired
    private IMemLevelService memLevelService;

    /**
     * 查询【会员等级】列表
     */
    @RequiresPermissions("member:level:list")
    @GetMapping("/list")
    public TableDataInfo list(MemLevel memLevel)
    {
        startPage();
        List<MemLevel> list = memLevelService.selectMemLevelList(memLevel);
        return getDataTable(list);
    }

    /**
     * 查询等级名称列表 /member/level/list/name
     */
    @RequiresPermissions("member:level:list")
    @GetMapping("/list/name")
    public AjaxResult listName()
    {
        List<LevelVo> list = memLevelService.getLevelList();
        return AjaxResult.success(list);
    }

    /**
     * 导出会员等级列表
     */
    @RequiresPermissions("member:level:export")
    @Log(title = "【导出会员等级列表】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemLevel memLevel)
    {
        List<MemLevel> list = memLevelService.selectMemLevelList(memLevel);
        ExcelUtil<MemLevel> util = new ExcelUtil<MemLevel>(MemLevel.class);
        util.exportExcel(response, list, "会员等级数据");
    }

    /**
     * 获取会员等级详细信息
     */
    @RequiresPermissions("member:level:query")
    @GetMapping(value = "/{levelId}")
    public AjaxResult getInfo(@PathVariable("levelId") Long levelId)
    {
        return AjaxResult.success(memLevelService.selectMemLevelByLevelId(levelId));
    }

    /**
     * 新增会员等级
     */
    @RequiresPermissions("member:level:add")
    @Log(title = "【新增会员等级】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody MemLevel memLevel)
    {
        return toAjax(memLevelService.insertMemLevel(memLevel));
    }

    /**
     * 修改会员等级
     */
    @RequiresPermissions("member:level:edit")
    @Log(title = "修改会员等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(UpdateGroup.class) @RequestBody MemLevel memLevel)
    {
        return toAjax(memLevelService.updateMemLevel(memLevel));
    }

    /**
     * 修改等级状态
     */
    @RequiresPermissions("member:level:edit")
    @Log(title = "修改等级状态", businessType = BusinessType.UPDATE)
    @PostMapping("/status")
    public AjaxResult editStatus(@RequestBody MemLevel memLevel)
    {
        return toAjax(memLevelService.updateMemLevel(memLevel));
    }

    /**
     * 删除会员等级
     */
    @RequiresPermissions("member:level:remove")
    @Log(title = "删除会员等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{levelIds}")
    public AjaxResult remove(@PathVariable Long[] levelIds)
    {
        return toAjax(memLevelService.deleteMemLevelByLevelIds(levelIds));
    }
}
