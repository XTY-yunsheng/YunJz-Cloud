package com.yunjz.account.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjz.account.vo.TypeNameVo;
import com.yunjz.account.mapper.AccTypeMapper;
import com.yunjz.account.service.IAccTypeService;
import com.yunjz.common.core.myconstant.ShowStatusEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunjz.account.domain.AccType;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author yunjz
 * @date 2022-09-18
 */
@Service
public class AccTypeServiceImpl extends ServiceImpl<AccTypeMapper, AccType> implements IAccTypeService
{
    @Autowired
    private AccTypeMapper accTypeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param typeId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AccType selectAccTypeByTypeId(Long typeId)
    {
        return accTypeMapper.selectAccTypeByTypeId(typeId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param accType 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AccType> selectAccTypeList(AccType accType)
    {
        return accTypeMapper.selectAccTypeList(accType);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param accType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAccType(AccType accType)
    {
        return accTypeMapper.insertAccType(accType);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param accType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAccType(AccType accType)
    {
        return accTypeMapper.updateAccType(accType);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param typeIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAccTypeByTypeIds(Long[] typeIds)
    {
        return accTypeMapper.deleteAccTypeByTypeIds(typeIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param typeId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAccTypeByTypeId(Long typeId)
    {
        return accTypeMapper.deleteAccTypeByTypeId(typeId);
    }

    /**
     * 查询账单类型名称列表
     * @param accType
     * @return
     */
    @Override
    public List<TypeNameVo> getTypeList(AccType accType) {
        List<AccType> types = this.list(new QueryWrapper<AccType>().eq("show_status", ShowStatusEnum.Enable.getCode()));
        List<TypeNameVo> typeNameVos = types.stream().map(type -> {
            TypeNameVo typeNameVo = new TypeNameVo();
            BeanUtils.copyProperties(type, typeNameVo);
            return typeNameVo;
        }).collect(Collectors.toList());
        return typeNameVos;
    }
}
