package com.yunjz.gen;

import com.yunjz.common.core.myutils.LogStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yunjz.common.security.annotation.EnableCustomConfig;
import com.yunjz.common.security.annotation.EnableRyFeignClients;
import com.yunjz.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author yunjz
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringBootApplication
public class YunJzGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YunJzGenApplication.class, args);
        System.out.println(LogStringUtils.getFormatLogString("(◠‿◠) 代码生成模块启动成功",34,0));
    }
}
