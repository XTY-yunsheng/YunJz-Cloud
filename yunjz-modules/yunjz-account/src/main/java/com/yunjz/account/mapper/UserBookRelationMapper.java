package com.yunjz.account.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunjz.account.domain.UserBookRelation;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface UserBookRelationMapper extends BaseMapper<UserBookRelation>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserBookRelation selectUserBookRelationById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userBookRelation 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserBookRelation> selectUserBookRelationList(UserBookRelation userBookRelation);

    /**
     * 新增【请填写功能名称】
     * 
     * @param userBookRelation 【请填写功能名称】
     * @return 结果
     */
    public int insertUserBookRelation(UserBookRelation userBookRelation);

    /**
     * 修改【请填写功能名称】
     * 
     * @param userBookRelation 【请填写功能名称】
     * @return 结果
     */
    public int updateUserBookRelation(UserBookRelation userBookRelation);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserBookRelationById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserBookRelationByIds(Long[] ids);
}
