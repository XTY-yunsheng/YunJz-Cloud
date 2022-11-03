package com.yunjz.system;

import com.yunjz.common.core.myutils.LogStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.yunjz.common.security.annotation.EnableCustomConfig;
import com.yunjz.common.security.annotation.EnableRyFeignClients;
import com.yunjz.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author yunjz
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YunJzSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YunJzSystemApplication.class, args);
        System.out.println(LogStringUtils.getFormatLogString("(◠‿◠) 系统模块启动成功",34,0));
    }
}
