package com.yunjz.member.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunjz.member.vo.LevelVo;
import com.yunjz.member.domain.MemLevel;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface IMemLevelService extends IService<MemLevel>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param levelId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public MemLevel selectMemLevelByLevelId(Long levelId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param memLevel 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<MemLevel> selectMemLevelList(MemLevel memLevel);

    /**
     * 新增【请填写功能名称】
     * 
     * @param memLevel 【请填写功能名称】
     * @return 结果
     */
    public int insertMemLevel(MemLevel memLevel);

    /**
     * 修改【请填写功能名称】
     * 
     * @param memLevel 【请填写功能名称】
     * @return 结果
     */
    public int updateMemLevel(MemLevel memLevel);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param levelIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteMemLevelByLevelIds(Long[] levelIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param levelId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMemLevelByLevelId(Long levelId);

    /**
     * 获取等级名称列表
     * @return
     */
    List<LevelVo> getLevelList();
}
