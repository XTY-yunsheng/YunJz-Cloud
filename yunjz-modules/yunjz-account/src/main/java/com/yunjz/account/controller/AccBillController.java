package com.yunjz.account.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.yunjz.account.vo.BillQueryVo;
import com.yunjz.account.vo.BillRespVo;
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
import com.yunjz.account.domain.AccBill;
import com.yunjz.account.service.IAccBillService;
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
@RequestMapping("/bill")
public class AccBillController extends BaseController
{
    @Autowired
    private IAccBillService accBillService;

    String pwd = "WlVoU05VMXFRWGROUkVWNVRXcEpQUT09";

    /**
     * 查询账单列表
     */
    @RequiresPermissions("account:bill:list")
    @GetMapping("/list")
    public TableDataInfo list(BillQueryVo billQueryVo)
    {
        startPage();
        List<BillRespVo> list = accBillService.selectAccBillList(billQueryVo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("account:bill:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BillQueryVo billQueryVo)
    {
        List<BillRespVo> list = accBillService.selectAccBillList(billQueryVo);
        ExcelUtil<BillRespVo> util = new ExcelUtil<BillRespVo>(BillRespVo.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("account:bill:query")
    @GetMapping(value = "/{billId}")
    public AjaxResult getInfo(@PathVariable("billId") Long billId)
    {
        return AjaxResult.success(accBillService.selectAccBillByBillId(billId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("account:bill:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(AddGroup.class) @RequestBody AccBill accBill)
    {
        return toAjax(accBillService.insertAccBill(accBill));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("account:bill:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(UpdateGroup.class) @RequestBody AccBill accBill)
    {
        return toAjax(accBillService.updateAccBill(accBill));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("account:bill:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{billIds}")
    public AjaxResult remove(@PathVariable Long[] billIds)
    {
        return toAjax(accBillService.deleteAccBillByBillIds(billIds));
    }
}
