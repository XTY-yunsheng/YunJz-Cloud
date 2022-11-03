package com.yunjz.account.vo;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author yunsheng
 * @create 2022-09-20 15:26
 */
@Data
public class BookQueryVo {
    private String bookName;
    private String userName;
    private Map<String, Date> gmtCreate;
}
