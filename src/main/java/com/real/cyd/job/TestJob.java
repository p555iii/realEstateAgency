package com.real.cyd.job;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Create in INtelliJ IDEA
 * Author cyd
 * Date   2018/1/2
 */
@Configuration
@EnableScheduling
public class TestJob {

        //@Scheduled(cron = "0/5 * * * * ?") // 每5秒执行一次
        public void scheduler() {
            System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
        }

}
