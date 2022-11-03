package com.yunjz.common.core.exception.file;

import com.yunjz.common.core.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author yunjz
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
