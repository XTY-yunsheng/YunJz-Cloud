package com.yunjz.account.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yunjz.common.core.annotation.Excel;
import com.yunjz.common.core.myvalid.AddGroup;
import com.yunjz.common.core.myvalid.UpdateGroup;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单模块 对象 acc_bill
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Data
public class AccBill
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId
    @NotNull(message = "修改必须指定账单id",groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    private Long billId;

    /** 账单号 */
    @Excel(name = "账单号")
    private String orderSn;

    /** 账单标题 */
    @NotBlank(message = "账单标题不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Excel(name = "账单标题")
    private String title;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 账本id */
    @Excel(name = "账本id")
    private Long bookId;

    /** 类型id */
    @Excel(name = "类型id")
    private Long typeId;

    /** 类别id */
    @Excel(name = "类别id")
    private Long categoryId;

    /** 金额 */
    @Excel(name = "金额")
    @Pattern(message = "金额格式不正确", regexp = "\\d+(.\\d{1,2})?")
    private BigDecimal money;

    /** 逻辑删除【0：未删，1：已删】 */
    @Excel(name = "逻辑删除【0：未删，1：已删】")
    @TableField
    private Integer deleted;

    /** 创建时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /** 更新时间 */
//    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmtModified;

}
