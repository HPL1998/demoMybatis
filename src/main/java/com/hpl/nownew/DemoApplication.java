package com.hpl.nownew;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication()
@EnableTransactionManagement
@MapperScan(basePackages = "com.hpl.nownew.mapping")


public class DemoApplication {
public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
}


}
