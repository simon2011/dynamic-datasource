package com.anson.dyanmic.datasource.test;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.anson"},exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.anson")
public class ApplicationStarter {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}

}
