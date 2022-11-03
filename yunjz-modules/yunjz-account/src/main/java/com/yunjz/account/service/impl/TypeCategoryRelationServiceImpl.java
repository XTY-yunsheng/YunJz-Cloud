package com.yunjz.account.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjz.account.mapper.AccCategoryMapper;
import com.yunjz.account.vo.TypeCatRelationVo;
import com.yunjz.account.domain.TypeCategoryRelation;
import com.yunjz.account.mapper.TypeCategoryRelationMapper;
import com.yunjz.account.service.ITypeCategoryRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Service
public class TypeCategoryRelationServiceImpl extends ServiceImpl<TypeCategoryRelationMapper, TypeCategoryRelation> implements ITypeCategoryRelationService
{
    @Autowired
    private TypeCategoryRelationMapper typeCategoryRelationMapper;

    @Autowired
    AccCategoryMapper accCategoryMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public TypeCategoryRelation selectTypeCategoryRelationById(Long id)
    {
        return typeCategoryRelationMapper.selectTypeCategoryRelationById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param typeCategoryRelation 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TypeCategoryRelation> selectTypeCategoryRelationList(TypeCategoryRelation typeCategoryRelation)
    {
        return typeCategoryRelationMapper.selectTypeCategoryRelationList(typeCategoryRelation);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param typeCategoryRelation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTypeCategoryRelation(TypeCategoryRelation typeCategoryRelation)
    {
        return typeCategoryRelationMapper.insertTypeCategoryRelation(typeCategoryRelation);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param typeCategoryRelation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTypeCategoryRelation(TypeCategoryRelation typeCategoryRelation)
    {
        return typeCategoryRelationMapper.updateTypeCategoryRelation(typeCategoryRelation);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTypeCategoryRelationByIds(Long[] ids)
    {
        return typeCategoryRelationMapper.deleteTypeCategoryRelationByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteTypeCategoryRelationById(Long id)
    {
        return typeCategoryRelationMapper.deleteTypeCategoryRelationById(id);
    }

    /**
     * 新增关联关系
     * @param vos
     */
    @Override
    public void saveBatchRelation(List<TypeCatRelationVo> vos) {
        List<TypeCategoryRelation> relations = vos.stream().map(relationVo -> {
            TypeCategoryRelation typeCategoryRelation = new TypeCategoryRelation();
            BeanUtils.copyProperties(relationVo, typeCategoryRelation);
            Long categoryId = relationVo.getCategoryId();
            Long typeId = relationVo.getTypeId();
            accCategoryMapper.updateTypeId(categoryId, typeId);
            return typeCategoryRelation;
        }).collect(Collectors.toList());
        this.saveBatch(relations);
    }

    /**
     * 删除类型&类别关联关系
     * @param vos
     */
    @Override
    public void deleteRelation(TypeCatRelationVo[] vos) {
        List<TypeCategoryRelation> relations = Arrays.asList(vos).stream().map(vo -> {
            TypeCategoryRelation typeCategoryRelation = new TypeCategoryRelation();
            BeanUtils.copyProperties(vo, typeCategoryRelation);
            Long categoryId = vo.getCategoryId();
            accCategoryMapper.updateTypeIdSetNull(categoryId);
            return typeCategoryRelation;
        }).collect(Collectors.toList());
        typeCategoryRelationMapper.deleteBatchRelation(relations);
    }
}
