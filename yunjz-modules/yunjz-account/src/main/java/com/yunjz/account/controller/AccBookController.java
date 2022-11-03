package com.yunjz.account.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.yunjz.account.vo.BookNameVo;
import com.yunjz.account.vo.BookRespVo;
import com.yunjz.account.vo.BookQueryVo;
import com.yunjz.account.vo.UserStatusTo;
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
import com.yunjz.account.domain.AccBook;
import com.yunjz.account.service.IAccBookService;
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
@RequestMapping("/book")
public class AccBookController extends BaseController
{
    @Autowired
    private IAccBookService accBookService;

    /**
     * 条件查询账本列表
     */
    @RequiresPermissions("account:book:list")
    @GetMapping("/list")
    public TableDataInfo list(BookQueryVo bookQueryVo)
    {
        startPage();
        List<BookRespVo> list = accBookService.selectAccBookList(bookQueryVo);
        return getDataTable(list);
    }

    /**
     * 根据用户id查询账本名称列表     /account/book/booknamelist/{userId}
     */
    @GetMapping("/booknamelist/{userId}")
    public AjaxResult listName(@PathVariable(value = "userId", required = false) Long userId)
    {
        List<BookNameVo> list = accBookService.getBookNameListByUserId(userId);
        return AjaxResult.success(list);
    }

    /**
     * 根据用户id和status修改当前用户所有账本状态(远程)     /book/status/feign
     */
    @PostMapping("/status/feign")
    public AjaxResult updateStatusFeign(@RequestBody UserStatusTo userStatusTo){
        accBookService.updateStatusFeign(userStatusTo);
        System.out.println("远程状态修改完成!!!");
        return AjaxResult.success();
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("account:book:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookQueryVo bookQueryVo)
    {
        List<BookRespVo> list = accBookService.selectAccBookList(bookQueryVo);
        ExcelUtil<BookRespVo> util = new ExcelUtil<BookRespVo>(BookRespVo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("account:book:query")
    @GetMapping(value = "/{bookId}")
    public AjaxResult getInfo(@PathVariable("bookId") Long bookId)
    {
        return AjaxResult.success(accBookService.selectAccBookByBookId(bookId));
    }

    /**
     * 新增账本
     */
//    @RequiresPermissions("account:book:add")
    @Log(title = "新增账本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody AccBook accBook)
    {
        accBookService.insertBookWithRelation(accBook);
        return AjaxResult.success();
    }

    /**
     * 修改账本
     */
//    @RequiresPermissions("account:book:edit")
    @Log(title = "修改账本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(UpdateGroup.class) @RequestBody AccBook accBook)
    {
        accBook.setGmtModified(new Date());
        return toAjax(accBookService.updateBookWithDefault(accBook));
    }

    /**
     * 删除账本，和关联关系
     */
    @RequiresPermissions("account:book:remove")
    @Log(title = "删除账本，和关联关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookIds}")
    public AjaxResult remove(@PathVariable Long[] bookIds)
    {
        accBookService.deleteBookWithRelationByBookIds(bookIds);
        return AjaxResult.success();
    }
}
