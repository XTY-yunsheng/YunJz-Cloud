package com.yunjz.modules.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 * 
 * @author yunjz
 */
@EnableAdminServer
@SpringBootApplication
public class YunJzMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YunJzMonitorApplication.class, args);
        System.out.println("(◠‿◠) 监控中心启动成功 \n");
    }
}
