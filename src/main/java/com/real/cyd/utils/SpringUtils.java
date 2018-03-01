package com.real.cyd.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-27 11:43
 **/
@WebListener
public class SpringUtils implements ServletContextListener {

    private static WebApplicationContext springContext;
    @Override
    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }
    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    public static ApplicationContext getApplicationContext() {
        return springContext;
    }

    public SpringUtils() {
    }


    public static <T> T getBean(Class<T> requiredType){

        if(springContext == null){

            throw new RuntimeException("springContext is null.");
        }
        return springContext.getBean(requiredType);
    }

}