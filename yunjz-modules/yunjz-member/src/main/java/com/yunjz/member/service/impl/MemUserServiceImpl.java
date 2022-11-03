package com.yunjz.member.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjz.common.core.myconstant.ShowStatusEnum;
import com.yunjz.common.core.utils.StringUtils;
import com.yunjz.common.core.web.domain.AjaxResult;
import com.yunjz.member.domain.MemLevel;
import com.yunjz.member.feign.AccountFeignService;
import com.yunjz.member.mapper.MemLevelMapper;
import com.yunjz.member.service.IMemLevelService;
import com.yunjz.member.vo.*;
import com.yunjz.member.service.IMemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunjz.member.mapper.MemUserMapper;
import com.yunjz.member.domain.MemUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Service
public class MemUserServiceImpl extends ServiceImpl<MemUserMapper, MemUser> implements IMemUserService
{
    @Autowired
    private MemUserMapper memUserMapper;

    @Autowired
    IMemLevelService memLevelService;

    @Autowired
    private MemLevelMapper memLevelMapper;

    @Autowired
    AccountFeignService accountFeignService;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public MemUser selectMemUserByUserId(Long userId)
    {
        return memUserMapper.selectMemUserByUserId(userId);
    }

    /**
     * 查询用户列表
     * 
     * @param userVo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserRespVo> selectMemUserList(UserVo userVo)
    {
        QueryWrapper<MemUser> wrapper = new QueryWrapper<>();
        String userName = userVo.getUserName();
        if (!StringUtils.isEmpty(userName)) {
            wrapper.and(w -> {
                w.like("user_name", userName);
            });
        }
        Long levelId = userVo.getLevelId();
        if (levelId != null) {
            wrapper.eq("level_id", levelId);
        }
        if (!StringUtils.isEmpty(userVo.getGmtCreate())) {
            Date min = userVo.getGmtCreate().get("min");
            if (min != null) {
                System.out.println("开始时间min: " + min);
                wrapper.ge("gmt_create", min);
            }
            Date max = userVo.getGmtCreate().get("max");
            if (max != null) {
                System.out.println("结束时间max: " + max);
                wrapper.le("gmt_create", max);
            }
        }

        List<MemUser> memUsers = memUserMapper.selectList(wrapper);

        if (!StringUtils.isEmpty(memUsers)) {
            List<UserRespVo> userRespVos = memUsers.stream().map(user -> {
                UserRespVo userRespVo = new UserRespVo();
                BeanUtils.copyProperties(user, userRespVo);
                //设置会员等级名称
                /*MemLevel level = memLevelMapper.selectMemLevelByLevelId(user.getLevelId());
                userRespVo.setLevelName(level.getName());*/
                MemLevel level = getLevelByIntegration(user.getIntegration());
                userRespVo.setLevelId(level.getLevelId());
                userRespVo.setLevelName(level.getName());
                return userRespVo;
            }).collect(Collectors.toList());
            System.out.println("用户信息查询完成!!!\n");
            return userRespVos;
        }


        return new ArrayList<UserRespVo>();
    }

    //根据积分值获取等级信息
    public MemLevel getLevelByIntegration(Long integration) {
        List<MemLevel> levelList = memLevelService.list(new QueryWrapper<MemLevel>().eq("show_status", ShowStatusEnum.Enable.getCode()));
        if (!StringUtils.isEmpty(levelList)) {
            List<MemLevel> collect = levelList.stream().sorted(Comparator.comparingLong(MemLevel::getIntegration)).collect(Collectors.toList());
            MemLevel level = new MemLevel();
            BeanUtils.copyProperties(collect.get(0),level);
            for (int i = 1; i < collect.size(); i++) {
                if (integration >= collect.get(i).getIntegration()){
                    BeanUtils.copyProperties(collect.get(i),level);
                }
            }
            System.out.println("方法==MemUserServiceImpl.getLevelByIntegration() -> 传入的积分值: " + integration + "\t返回的等级信息: " + level.toString());
            return level;
        }
        return null;
    }

    /**
     * 新增用户
     * 
     * @param memUser 新增用户
     * @return 结果
     */
    @Override
    public int insertMemUser(MemUser memUser)
    {

        MemLevel levelByIntegration = getLevelByIntegration(memUser.getIntegration());
        memUser.setLevelId(levelByIntegration.getLevelId());
        return memUserMapper.insertMemUser(memUser);
    }

    /**
     * 修改用户
     * 
     * @param memUser 修改用户
     * @return 结果
     */
    @Override
    public int updateMemUser(MemUser memUser)
    {
        Long integration = (memUser.getIntegration() != null) ? memUser.getIntegration() : 0;
        MemLevel levelByIntegration = getLevelByIntegration(integration);
        memUser.setLevelId(levelByIntegration.getLevelId());
        return memUserMapper.updateMemUser(memUser);
    }

    /**
     * 批量删除用户
     * 
     * @param userIds 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteMemUserByUserIds(Long[] userIds)
    {
        return memUserMapper.deleteMemUserByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMemUserByUserId(Long userId)
    {
        return memUserMapper.deleteMemUserByUserId(userId);
    }

    /**
     * 查询可用用户名称列表
     * @return 结果
     */
    @Override
    public List<UserNameVo> getUserNameList() {
        List<MemUser> memUsers = memUserMapper.selectList(new QueryWrapper<MemUser>().eq("show_status",ShowStatusEnum.Enable.getCode()));
        List<UserNameVo> userNameVos = memUsers.stream().map(memUser -> {
            UserNameVo userNameVo = new UserNameVo();
            BeanUtils.copyProperties(memUser, userNameVo);
            return userNameVo;
        }).collect(Collectors.toList());
        return userNameVos;
    }

    /**
     * 修改用户状态
     */
    @Transactional
    @Override
    public void updateMemUserStatus(MemUser memUser) {
        UserStatusTo userStatusTo = new UserStatusTo();
        BeanUtils.copyProperties(memUser,userStatusTo);
        this.baseMapper.updateShowStatus(userStatusTo);
        accountFeignService.updateStatusByUserId(userStatusTo);
    }

    /**
     * 查询可用用户列表详细信息(远程)
     */
    @Override
    public List<Long> feignUserList(UserIdTo userIdTo) {
        List<MemUser> memUsers = this.list(new QueryWrapper<MemUser>().eq("show_status", ShowStatusEnum.Enable.getCode()).eq("deleted", 0));
        if (!StringUtils.isEmpty(memUsers)) {
            return memUsers.stream().map(MemUser::getUserId).collect(Collectors.toList());
        }
        return new ArrayList<Long>();
    }

}
