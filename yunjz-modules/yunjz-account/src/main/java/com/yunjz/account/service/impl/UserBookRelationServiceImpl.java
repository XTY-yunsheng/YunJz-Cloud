package com.yunjz.account.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjz.account.domain.AccBook;
import com.yunjz.account.service.IAccBookService;
import com.yunjz.account.vo.UserNameVo;
import com.yunjz.account.domain.UserBookRelation;
import com.yunjz.account.mapper.UserBookRelationMapper;
import com.yunjz.account.service.IUserBookRelationService;
import com.yunjz.common.core.myconstant.ShowStatusEnum;
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
public class UserBookRelationServiceImpl extends ServiceImpl<UserBookRelationMapper, UserBookRelation> implements IUserBookRelationService
{
    @Autowired
    private UserBookRelationMapper userBookRelationMapper;

    @Autowired
    IAccBookService accBookService;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserBookRelation selectUserBookRelationById(Long id)
    {
        return userBookRelationMapper.selectUserBookRelationById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userBookRelation 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserBookRelation> selectUserBookRelationList(UserBookRelation userBookRelation)
    {
        return userBookRelationMapper.selectUserBookRelationList(userBookRelation);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param userBookRelation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserBookRelation(UserBookRelation userBookRelation)
    {
        return userBookRelationMapper.insertUserBookRelation(userBookRelation);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param userBookRelation 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserBookRelation(UserBookRelation userBookRelation)
    {
        return userBookRelationMapper.updateUserBookRelation(userBookRelation);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserBookRelationByIds(Long[] ids)
    {
        return userBookRelationMapper.deleteUserBookRelationByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserBookRelationById(Long id)
    {
        return userBookRelationMapper.deleteUserBookRelationById(id);
    }

    /**
     * 查询可用用户名称列表
     * @return
     */
    @Override
    public List<UserNameVo> getUserNameList() {
        List<AccBook> accBooks = accBookService.list(new QueryWrapper<AccBook>().eq("show_status", ShowStatusEnum.Enable.getCode()));
        List<Long> userIds = accBooks.stream().map(AccBook::getUserId).distinct().collect(Collectors.toList());
        List<UserBookRelation> relations = userBookRelationMapper.selectList(new QueryWrapper<UserBookRelation>().in("user_id", userIds));
        List<UserNameVo> userNameVos = relations.stream().map(relation -> {
            UserNameVo userNameVo = new UserNameVo();
            BeanUtils.copyProperties(relation, userNameVo);
            return userNameVo;
        }).distinct().collect(Collectors.toList());
        return userNameVos;
    }
}
