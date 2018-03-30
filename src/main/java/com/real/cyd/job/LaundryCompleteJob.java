package com.real.cyd.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: realEstateAgency
 * @description: 洗衣job
 * @author: cyd
 * @create: 2018-03-28 16:05
 **/
@Configuration
@EnableScheduling
public class LaundryCompleteJob {

    //@Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
    public void scheduler() {

    }
}