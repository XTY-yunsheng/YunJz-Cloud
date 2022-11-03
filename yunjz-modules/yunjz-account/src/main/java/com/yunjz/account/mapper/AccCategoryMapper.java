package com.yunjz.account.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunjz.account.domain.AccCategory;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface AccCategoryMapper extends BaseMapper<AccCategory>
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
    public List<AccCategory> selectAccCategoryList(AccCategory accCategory);

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
     * 删除【请填写功能名称】
     * 
     * @param categoryId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAccCategoryByCategoryId(Long categoryId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccCategoryByCategoryIds(Long[] categoryIds);

    void updateTypeId(@Param("categoryId") Long categoryId, @Param("typeId") Long typeId);

    void updateTypeIdSetNull(@Param("categoryId") Long categoryId);
}
