package com.yunjz.account.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunjz.account.domain.UserBookRelation;
import com.yunjz.account.vo.UserNameVo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface IUserBookRelationService extends IService<UserBookRelation>
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
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteUserBookRelationByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserBookRelationById(Long id);

    /**
     * 查询可用用户名称列表
     * @return
     */
    List<UserNameVo> getUserNameList();

}
