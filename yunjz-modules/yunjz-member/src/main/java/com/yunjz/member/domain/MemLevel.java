package com.yunjz.member.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.yunjz.common.core.myvalid.AddGroup;
import com.yunjz.common.core.myvalid.UpdateGroup;
import lombok.Data;
import com.yunjz.common.core.annotation.Excel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 【请填写功能名称】对象 mem_level
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Data
public class MemLevel
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId
    @NotNull(message = "修改必须指定等级id",groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    private Long levelId;

    /** 等级名称 */
    @NotBlank(message = "等级名称不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Excel(name = "等级名称")
    private String name;

    /** 所需积分值 */
    @NotNull(message = "新增时必须指定积分值", groups={AddGroup.class})
    @Min(value = 0,message = "积分值必须大于等于0",groups={AddGroup.class,UpdateGroup.class})
    @Excel(name = "所需积分值")
    private Long integration;

    /** 是否为默认等级[0->不是；1->是] */
    @Excel(name = "是否为默认等级[0->不是；1->是]")
    private Long defaultStatus;

    /** 启用状态【0：启用，1：禁用】 */
    @Excel(name = "启用状态【0：启用，1：禁用】")
    private Integer showStatus;

}
