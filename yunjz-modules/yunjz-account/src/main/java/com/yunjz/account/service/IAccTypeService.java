package com.yunjz.account.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunjz.account.domain.AccType;
import com.yunjz.account.vo.TypeNameVo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface IAccTypeService extends IService<AccType>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param typeId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AccType selectAccTypeByTypeId(Long typeId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param accType 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AccType> selectAccTypeList(AccType accType);

    /**
     * 新增【请填写功能名称】
     * 
     * @param accType 【请填写功能名称】
     * @return 结果
     */
    public int insertAccType(AccType accType);

    /**
     * 修改【请填写功能名称】
     * 
     * @param accType 【请填写功能名称】
     * @return 结果
     */
    public int updateAccType(AccType accType);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param typeIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAccTypeByTypeIds(Long[] typeIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param typeId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAccTypeByTypeId(Long typeId);

    /**
     * 查询账单类型名称列表
     * @param accType
     * @return
     */
    List<TypeNameVo> getTypeList(AccType accType);
}
