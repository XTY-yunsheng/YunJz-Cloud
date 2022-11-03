package com.yunjz.member.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunjz.member.domain.MemUser;
import com.yunjz.member.vo.UserIdTo;
import com.yunjz.member.vo.UserNameVo;
import com.yunjz.member.vo.UserRespVo;
import com.yunjz.member.vo.UserVo;

/**
 * 用户列表Service接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface IMemUserService extends IService<MemUser>
{
    /**
     * 查询用户列表
     * 
     * @param userId 用户列表 主键
     * @return
     */
    public MemUser selectMemUserByUserId(Long userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userVo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserRespVo> selectMemUserList(UserVo userVo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param memUser 【请填写功能名称】
     * @return 结果
     */
    public int insertMemUser(MemUser memUser);

    /**
     * 修改【请填写功能名称】
     * 
     * @param memUser 【请填写功能名称】
     * @return 结果
     */
    public int updateMemUser(MemUser memUser);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteMemUserByUserIds(Long[] userIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMemUserByUserId(Long userId);

    /**
     * 查询可用用户名称列表
     * @return
     */
    List<UserNameVo> getUserNameList();

    /**
     * 修改用户状态
     * @param memUser
     */
    void updateMemUserStatus(MemUser memUser);

    /**
     * 查询可用用户列表详细信息(远程)
     * @param userIdTo
     * @return
     */
    List<Long> feignUserList(UserIdTo userIdTo);

    /**
     * 条件查询【用户】列表
     * @param userVo
     * @return
     */
//    List<UserRespVo> getListByCondition(UserVo userVo);
}
