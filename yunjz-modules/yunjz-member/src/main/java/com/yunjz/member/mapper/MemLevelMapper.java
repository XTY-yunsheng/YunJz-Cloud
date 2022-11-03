package com.yunjz.member.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunjz.member.domain.MemLevel;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author yunjz
 * @date 2022-09-18
 */
public interface MemLevelMapper extends BaseMapper<MemLevel>
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
     * 删除【请填写功能名称】
     * 
     * @param levelId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteMemLevelByLevelId(Long levelId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param levelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemLevelByLevelIds(Long[] levelIds);
}
