package org.example.springbootdemo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("org.example.springbootdemo1.mapper")
public class SpringBootDemo1Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemo1Application.class, args);
    }
}
