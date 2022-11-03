package com.yunjz.account.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunjz.account.domain.AccBill;
import com.yunjz.account.vo.BillQueryVo;
import com.yunjz.account.vo.BillRespVo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface IAccBillService extends IService<AccBill>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param billId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AccBill selectAccBillByBillId(Long billId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param billQueryVo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BillRespVo> selectAccBillList(BillQueryVo billQueryVo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param accBill 【请填写功能名称】
     * @return 结果
     */
    public int insertAccBill(AccBill accBill);

    /**
     * 修改【请填写功能名称】
     * 
     * @param accBill 【请填写功能名称】
     * @return 结果
     */
    public int updateAccBill(AccBill accBill);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param billIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAccBillByBillIds(Long[] billIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param billId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAccBillByBillId(Long billId);
}
