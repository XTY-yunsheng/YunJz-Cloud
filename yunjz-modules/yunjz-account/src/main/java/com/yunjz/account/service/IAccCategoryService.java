package com.yunjz.account.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunjz.account.domain.AccCategory;
import com.yunjz.account.vo.CategoryNameVo;
import com.yunjz.account.vo.CategoryRespVo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface IAccCategoryService extends IService<AccCategory>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param categoryId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AccCategory selectAccCategoryByCategoryId(Long categoryId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param accCategory 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CategoryRespVo> selectAccCategoryList(AccCategory accCategory);

    /**
     * 新增【请填写功能名称】
     * 
     * @param accCategory 【请填写功能名称】
     * @return 结果
     */
    public int insertAccCategory(AccCategory accCategory);

    /**
     * 修改【请填写功能名称】
     * 
     * @param accCategory 【请填写功能名称】
     * @return 结果
     */
    public int updateAccCategory(AccCategory accCategory);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param categoryIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteAccCategoryByCategoryIds(Long[] categoryIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param categoryId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAccCategoryByCategoryId(Long categoryId);

    /**
     * 新增账单类别，并添加关联关系
     * @param accCategory
     */
    void addCategoryWithRelation(AccCategory accCategory);

    /**
     * 删除账单类别，和关联关系
     * @param categoryIds
     */
    void deleteCategoryWithRelationByCategoryIds(Long[] categoryIds);

    /**
     * 根据typeId查询关联类别列表
     * @param typeId
     * @return
     */
    List<AccCategory> getRelationCategroy(Long typeId);

    /**
     * 根据typeId查询可以关联的类别列表
     * @param accCategory
     * @param typeId
     * @return
     */
    List<AccCategory> getNoRelationCategroy(AccCategory accCategory, Long typeId);

    /**
     * 根据typeId获取类别名称列表
     * @param typeId
     * @return
     */
    List<CategoryNameVo> getCategoryNameListByTypeId(Long typeId);

}
