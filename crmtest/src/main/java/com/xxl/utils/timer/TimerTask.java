package com.xxl.utils.timer;

import cn.hutool.extra.mail.MailAccount;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {
    @Scheduled(cron = "0/1 * * * * ?")//每隔2秒隔行一次
    public void test2()
    {
        System.out.println("5秒时间到了，开始执行扫描接口任务了");
    }
}
