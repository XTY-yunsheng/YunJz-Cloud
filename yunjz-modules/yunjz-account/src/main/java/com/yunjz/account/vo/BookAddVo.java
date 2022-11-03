package com.yunjz.account.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yunsheng
 * @create 2022-09-20 20:53
 */
@Data
public class BookAddVo {

    private Long bookId;
    private String bookName;
    private Long userId;
    private Integer defaultStatus;
    private Integer showStatus;
    private Integer deleted;
    private Date gmtCreate;
    private Date gmtModified;
}
