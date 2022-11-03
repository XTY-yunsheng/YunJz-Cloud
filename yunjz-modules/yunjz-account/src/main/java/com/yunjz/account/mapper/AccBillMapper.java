package com.yunjz.account.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunjz.account.domain.AccBill;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface AccBillMapper extends BaseMapper<AccBill>
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
     * @param accBill 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AccBill> selectAccBillList(AccBill accBill);

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
     * 删除【请填写功能名称】
     * 
     * @param billId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAccBillByBillId(Long billId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param billIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccBillByBillIds(Long[] billIds);
}
