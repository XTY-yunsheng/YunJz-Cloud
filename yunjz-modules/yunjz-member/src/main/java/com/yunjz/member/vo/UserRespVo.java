package com.yunjz.member.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yunsheng
 * @create 2022-09-19 13:31
 */
@Data
public class UserRespVo {

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
    private Date gmtCreate;
    private Date gmtModified;
    private String socialUid;
    private String accessToken;
    private String expiresIn;

    private String levelName;
}
