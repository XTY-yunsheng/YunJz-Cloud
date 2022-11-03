package com.yunjz.job;

import com.yunjz.common.core.myutils.LogStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yunjz.common.security.annotation.EnableCustomConfig;
import com.yunjz.common.security.annotation.EnableRyFeignClients;
import com.yunjz.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 定时任务
 * 
 * @author yunjz
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class YunJzJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YunJzJobApplication.class, args);
        System.out.println(LogStringUtils.getFormatLogString("(◠‿◠) 定时任务模块启动成功",34,0));
    }
}
