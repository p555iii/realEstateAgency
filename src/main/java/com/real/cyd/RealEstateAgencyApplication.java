package com.real.cyd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//读取Scheduled 定时任务注解  开始定时任务
@EnableScheduling
//扫描包下mapper文件
@MapperScan("com.real.cyd.mapper")
@ServletComponentScan
public class RealEstateAgencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstateAgencyApplication.class, args);
	}
}
