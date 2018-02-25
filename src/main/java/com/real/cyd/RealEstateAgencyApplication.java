package com.real.cyd;

import com.real.cyd.bean.PropBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
//读取Scheduled 定时任务注解  开始定时任务
@EnableScheduling
//扫描包下mapper文件
@MapperScan("com.real.cyd.mapper")
@ServletComponentScan
@EnableConfigurationProperties(PropBean.class)
public class RealEstateAgencyApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(RealEstateAgencyApplication.class);
		/*
		 * Banner.Mode.OFF:关闭;
		 * Banner.Mode.CONSOLE:控制台输出，默认方式;
		 * Banner.Mode.LOG:日志输出方式;
		 */
		//application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
		factory.setMaxFileSize("128MB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("128MB");
		//Sets the directory location where files will be stored.
		//factory.setLocation("路径地址");
		return factory.createMultipartConfig();
	}
}
