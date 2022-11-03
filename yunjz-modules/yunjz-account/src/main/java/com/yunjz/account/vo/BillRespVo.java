package com.yunjz.account.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yunsheng
 * @create 2022-09-26 19:08
 */
@Data
public class BillRespVo {
    private Long billId;
    private String orderSn;
    private String title;
    private Long userId;
    private String userName;
    private Long bookId;
    private String bookName;
    private String typeName;
    private String categoryName;
    private BigDecimal money;
    private Integer deleted;
    private Date gmtCreate;
    private Date gmtModified;
}
