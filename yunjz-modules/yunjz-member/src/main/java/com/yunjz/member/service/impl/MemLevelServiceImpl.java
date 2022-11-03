package com.yunjz.member.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunjz.common.core.myconstant.ShowStatusEnum;
import com.yunjz.member.vo.LevelVo;
import com.yunjz.member.service.IMemLevelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunjz.member.mapper.MemLevelMapper;
import com.yunjz.member.domain.MemLevel;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Service
public class MemLevelServiceImpl extends ServiceImpl<MemLevelMapper, MemLevel> implements IMemLevelService
{
    @Autowired
    private MemLevelMapper memLevelMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param levelId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public MemLevel selectMemLevelByLevelId(Long levelId)
    {
        return memLevelMapper.selectMemLevelByLevelId(levelId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param memLevel 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<MemLevel> selectMemLevelList(MemLevel memLevel)
    {
        return memLevelMapper.selectMemLevelList(memLevel);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param memLevel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertMemLevel(MemLevel memLevel)
    {
        return memLevelMapper.insertMemLevel(memLevel);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param memLevel 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateMemLevel(MemLevel memLevel)
    {
        return memLevelMapper.updateMemLevel(memLevel);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param levelIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMemLevelByLevelIds(Long[] levelIds)
    {
        return memLevelMapper.deleteMemLevelByLevelIds(levelIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param levelId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteMemLevelByLevelId(Long levelId)
    {
        return memLevelMapper.deleteMemLevelByLevelId(levelId);
    }

    /**
     * 获取可用等级列表
     * @return
     */
    @Override
    public List<LevelVo> getLevelList() {
        List<MemLevel> levels = this.list(new QueryWrapper<MemLevel>().eq("show_status", ShowStatusEnum.Enable.getCode()));
        List<LevelVo> levelVos = levels.stream().map(level -> {
            LevelVo levelVo = new LevelVo();
            BeanUtils.copyProperties(level, levelVo);
            return levelVo;
        }).collect(Collectors.toList());
        return levelVos;
    }
}
