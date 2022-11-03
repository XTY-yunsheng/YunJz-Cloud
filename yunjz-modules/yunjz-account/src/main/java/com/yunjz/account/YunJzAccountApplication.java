package com.yunjz.account;

import com.yunjz.common.core.myutils.LogStringUtils;
import com.yunjz.common.security.annotation.EnableCustomConfig;
import com.yunjz.common.security.annotation.EnableRyFeignClients;
import com.yunjz.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import static java.lang.Math.pow;

/**
 * 账单模块
 * 
 * @author yunjz
 */
//@EnableFeignClients(basePackages = "com.yunjz.account.feign")
@EnableDiscoveryClient
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class YunJzAccountApplication
{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(YunJzAccountApplication.class, args);
        Thread.sleep(3000);
        int hx = 0;
        for (float i = 2.5f; i > -2.5f; i -= 0.14f) {
            for (float j = -2.3f; j < 2.3f; j +=0.038f) {
                float k = j*j + i*i -4f;
                if ((pow(k,3)-pow(j,2)*pow(i,3))<-0.00f) {
                    String str = "I love you!";
                    int num = hx % str.length();
                    System.out.print("\033[1;31m" + str.charAt(num) + "\033[0m");
                    hx++;
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
            Thread.sleep(150);
        }
        System.out.println(LogStringUtils.getFormatLogString("(◠‿◠) 账单模块启动成功",34,0));
    }
}
