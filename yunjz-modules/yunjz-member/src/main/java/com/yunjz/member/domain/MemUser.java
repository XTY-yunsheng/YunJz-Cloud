package com.yunjz.member.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunjz.common.core.myvalid.AddGroup;
import com.yunjz.common.core.myvalid.UpdateGroup;
import lombok.Data;
import com.yunjz.common.core.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 【请填写功能名称】对象 mem_user
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Data
public class MemUser
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId
    @NotNull(message = "修改必须指定用户id",groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    private Long userId;

    /** 用户名 */
    @NotBlank(message = "用户名不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Excel(name = "用户名")
    private String userName;

    /** 密码 */
    @NotBlank(message = "密码不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @Excel(name = "密码")
    private String password;

    /** 微信openid */
    @Excel(name = "微信openid")
    private String openid;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 会员等级id */
    @Excel(name = "会员等级id")
    private Long levelId;

    /** 积分值 */
    @NotNull(message = "新增时必须指定积分值", groups={AddGroup.class})
    @Min(value = 0,message = "积分值必须大于等于0",groups={AddGroup.class,UpdateGroup.class})
    @Excel(name = "积分值")
    private Long integration;

    /** 性别【0：男，1：女】 */
    @Excel(name = "性别【0：男，1：女】")
    private Long gender;

    /** 生日 */
    @NotNull(message = "生日不能为空",groups = {AddGroup.class,UpdateGroup.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birth;

    /** 个性签名 */
    @Excel(name = "个性签名")
    private String sign;

    /** 启用状态【0：启用，1：禁用】 */
    @Excel(name = "启用状态【0：启用，1：禁用】")
    private Integer showStatus;

    /** 逻辑删除【0：未删，1：已删】 */
    @Excel(name = "逻辑删除【0：未删，1：已删】")
    @TableLogic
    private Integer deleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

    /** 社交用户的唯一id */
    @Excel(name = "社交用户的唯一id")
    private String socialUid;

    /** 访问令牌 */
    @Excel(name = "访问令牌")
    private String accessToken;

    /** 令牌的过期时间 */
    @Excel(name = "令牌的过期时间")
    private String expiresIn;

}
