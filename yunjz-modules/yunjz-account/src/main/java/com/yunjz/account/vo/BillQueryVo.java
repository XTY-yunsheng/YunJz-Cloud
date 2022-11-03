package com.yunjz.account.vo;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author yunsheng
 * @create 2022-09-26 19:01
 */
@Data
public class BillQueryVo {
    private String title;
    private Long userId;
    private Long bookId;
    private Long typeId;
    private Long categoryId;
    private Map<String, Date> gmtCreate;
}
