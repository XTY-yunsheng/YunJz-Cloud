package com.yunjz.common.core.exception.user;

import com.yunjz.common.core.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author yunjz
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
