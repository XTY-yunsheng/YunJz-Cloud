package com.yunjz.member.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yunsheng
 * @create 2022-09-19 19:04
 */
@Data
public class UserVo {

    private Long userId;
    private String userName;
    private String password;
    private String openid;
    private String mobile;
    private String nickname;
    private Long levelId;
    private Long integration;
    private Long gender;
    private Date birth;
    private String sign;
    private Integer showStatus;
    private Integer deleted;
    private Map<String, Date> gmtCreate;
    private Date gmtModified;
    private String socialUid;
    private String accessToken;
    private String expiresIn;
}
