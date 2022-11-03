package com.yunjz.account.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import com.yunjz.common.core.annotation.Excel;

/**
 * 【请填写功能名称】对象 user_book_relation
 * 
 * @author yunjz
 * @date 2022-09-18
 */
@Data
@Accessors(chain = true)
public class UserBookRelation
{
    private static final long serialVersionUID = 1L;

    /** id */
    @TableId
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 账本id */
    @Excel(name = "账本id")
    private Long bookId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 账本名 */
    @Excel(name = "账本名")
    private String bookName;

}
