package com.yunjz.account.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yunsheng
 * @create 2022-09-20 19:52
 */
@Data
public class UserIdTo {
    private Long userId;
    private List<Long> userIds;
    private String userName;
}
