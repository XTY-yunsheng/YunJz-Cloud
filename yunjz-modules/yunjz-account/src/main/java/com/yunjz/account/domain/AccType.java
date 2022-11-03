package com.yunjz.account.domain;

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
 * 【请填写功能名称】对象 acc_type
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Data
public class AccType
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId
    @NotNull(message = "修改必须指定类型id",groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    private Long typeId;

    /** 账单类型名称 */
    @NotBlank(message = "账单类型名称不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Excel(name = "账单类型名称")
    private String typeName;

    /** 启用状态【0：启用，1：禁用】 */
    @Excel(name = "启用状态【0：启用，1：禁用】")
    private Integer showStatus;

    /** 排序 */
    @NotNull(message = "排序字段不能为空", groups={AddGroup.class})
    @Min(value = 0,message = "排序必须大于等于0",groups={AddGroup.class,UpdateGroup.class})
    @Excel(name = "排序")
    private Long sort;

}
