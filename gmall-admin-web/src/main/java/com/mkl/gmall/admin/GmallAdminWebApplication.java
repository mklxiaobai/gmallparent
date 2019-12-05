package com.mkl.gmall.admin;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GmallAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallAdminWebApplication.class, args);
    }

}
