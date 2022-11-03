package com.yunjz.account.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunjz.common.core.myvalid.AddGroup;
import com.yunjz.common.core.myvalid.DefaultStatus;
import com.yunjz.common.core.myvalid.UpdateGroup;
import lombok.Data;
import com.yunjz.common.core.annotation.Excel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 【请填写功能名称】对象 acc_book
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Data
public class AccBook
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId
    @NotNull(message = "修改必须指定账本id",groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    private Long bookId;

    /** 账本名称 */
    @NotBlank(message = "账本名称不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Excel(name = "账本名称")
    private String bookName;

    /** 用户id */
    @NotNull(message = "必须指定用户",groups = {AddGroup.class,UpdateGroup.class})
    @Excel(name = "用户id")
    private Long userId;

    /** 是否为默认等级[0->不是；1->是] */
    @NotNull(message = "必须指定是否为默认等级", groups = {AddGroup.class, UpdateGroup.class})
    @DefaultStatus(vals={0,1},groups = {AddGroup.class, UpdateGroup.class})
    @Excel(name = "是否为默认等级[0->不是；1->是]")
    private Integer defaultStatus;

    /** 启用状态【0：启用，1：禁用】 */
    @Excel(name = "启用状态【0：启用，1：禁用】")
    private Integer showStatus;

    /** 逻辑删除【0：未删，1：已删】 */
    @Excel(name = "逻辑删除【0：未删，1：已删】")
    @TableLogic
    private Integer deleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

}
