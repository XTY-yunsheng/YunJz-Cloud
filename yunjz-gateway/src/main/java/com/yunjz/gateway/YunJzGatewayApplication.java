package com.yunjz.gateway;

import com.yunjz.common.core.myutils.LogStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 * 
 * @author yunjz
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class YunJzGatewayApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YunJzGatewayApplication.class, args);
        System.out.println(LogStringUtils.getFormatLogString("(◠‿◠) 网关服务启动成功",34,0));
    }
}
