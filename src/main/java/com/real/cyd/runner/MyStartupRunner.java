package com.real.cyd.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Create in INtelliJ IDEA
 * Author cyd
 * Date   2018/1/4
 * 服务启动会调用
 */
@Component
//@Order(value=2) 如果有多个runner order表示启动顺序 越小 优先级越高
public class MyStartupRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
    }
}
