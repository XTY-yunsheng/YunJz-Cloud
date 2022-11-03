package com.yunjz.member.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author yunsheng
 * @create 2022-10-17 18:07
 */
@Data
@ToString
public class UserStatusTo {
    private Long userId;
    private Integer showStatus;
}
