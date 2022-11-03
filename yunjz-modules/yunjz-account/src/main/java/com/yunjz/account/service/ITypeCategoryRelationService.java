package com.yunjz.account.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunjz.account.domain.TypeCategoryRelation;
import com.yunjz.account.vo.TypeCatRelationVo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface ITypeCategoryRelationService extends IService<TypeCategoryRelation>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public TypeCategoryRelation selectTypeCategoryRelationById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param typeCategoryRelation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TypeCategoryRelation> selectTypeCategoryRelationList(TypeCategoryRelation typeCategoryRelation);

    /**
     * 新增【请填写功能名称】
     * 
     * @param typeCategoryRelation 【请填写功能名称】
     * @return 结果
     */
    public int insertTypeCategoryRelation(TypeCategoryRelation typeCategoryRelation);

    /**
     * 修改【请填写功能名称】
     * 
     * @param typeCategoryRelation 【请填写功能名称】
     * @return 结果
     */
    public int updateTypeCategoryRelation(TypeCategoryRelation typeCategoryRelation);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteTypeCategoryRelationByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTypeCategoryRelationById(Long id);

    /**
     * 新增关联关系
     * @param vos
     */
    void saveBatchRelation(List<TypeCatRelationVo> vos);

    /**
     * 删除类型&类别关联关系
     * @param vos
     */
    void deleteRelation(TypeCatRelationVo[] vos);
}
