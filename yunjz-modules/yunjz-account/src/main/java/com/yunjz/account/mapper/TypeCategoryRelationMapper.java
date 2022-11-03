package com.yunjz.account.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunjz.account.domain.TypeCategoryRelation;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface TypeCategoryRelationMapper extends BaseMapper<TypeCategoryRelation>
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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteTypeCategoryRelationById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTypeCategoryRelationByIds(Long[] ids);

    /**
     * 删除类型&类别关联关系
     * @param relations
     */
    void deleteBatchRelation(@Param("relations") List<TypeCategoryRelation> relations);
}
