package com.yunjz.file;

import com.yunjz.common.core.myutils.LogStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.yunjz.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 * 
 * @author yunjz
 */
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class YunJzFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YunJzFileApplication.class, args);
        System.out.println(LogStringUtils.getFormatLogString("(◠‿◠) 文件服务模块启动成功",34,0));
    }
}
