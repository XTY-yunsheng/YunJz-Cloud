package com.yunjz.member.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

/**
 * @author yunsheng
 * @create 2022-09-18 16:10
 */
@Configuration
@EnableTransactionManagement //开启事务
@MapperScan("com.yunjz.member.mapper")
public class MybatisConfig {

    //引入分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInterceptor.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(1000);
        return paginationInterceptor;
    }

    // MetaObjectHandler 这接口有很多方法，一般情况下只需要实现这两个方法就行了
    // 其他方法感兴趣的可以自己进去看下
    @Component
    public class MyFillHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            //log.info("start insert fill ....");
            // 起始版本 3.3.0(推荐使用)
            this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        }
        @Override
        public void updateFill(MetaObject metaObject) {
            //log.info("start update fill ....");
            // 起始版本 3.3.0(推荐)
            this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        }
    }
}
