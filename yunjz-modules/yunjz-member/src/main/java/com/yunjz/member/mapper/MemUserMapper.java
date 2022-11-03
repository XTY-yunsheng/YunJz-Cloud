package com.yunjz.member.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunjz.member.domain.MemUser;
import com.yunjz.member.vo.UserStatusTo;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface MemUserMapper extends BaseMapper<MemUser>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MemUser selectMemUserByUserId(Long userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param memUser 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MemUser> selectMemUserList(MemUser memUser);

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
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMemUserByUserId(Long userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemUserByUserIds(Long[] userIds);

    /**
     * 修改用户状态
     * @param userStatusTo
     */
    void updateShowStatus(@Param("userStatusTo") UserStatusTo userStatusTo);
}
