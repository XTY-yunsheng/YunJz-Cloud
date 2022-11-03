package com.yunjz.account.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yunsheng
 * @create 2022-09-20 15:20
 */
@Data
public class BookRespVo {
    private Long bookId;
    private String bookName;
    private Integer defaultStatus;
    private Integer showStatus;
    private Integer deleted;
    private Date gmtCreate;
    private Date gmtModified;

    private String userName;
}
