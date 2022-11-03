package com.yunjz.member;

import com.yunjz.common.core.myutils.LogStringUtils;
import com.yunjz.common.security.annotation.EnableCustomConfig;
import com.yunjz.common.security.annotation.EnableRyFeignClients;
import com.yunjz.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 会员模块
 * 
 * @author yunjz
 */
@EnableDiscoveryClient
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YunJzMemberApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YunJzMemberApplication.class, args);
        System.out.println(LogStringUtils.getFormatLogString("(◠‿◠) 会员模块启动成功",34,0));
    }
}
