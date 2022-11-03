package com.yunjz.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.yunjz.common.security.annotation.EnableRyFeignClients;
import com.yunjz.common.core.myutils.LogStringUtils;

/**
 * 认证授权中心
 * 
 * @author yunjz
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class YunJzAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YunJzAuthApplication.class, args);
        System.out.println(LogStringUtils.getFormatLogString("(◠‿◠) 认证授权中心启动成功",34,0));
    }

}
