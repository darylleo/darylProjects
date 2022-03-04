package com.daryl.config;

import com.daryl.paperline.AnJuKePagePipeline;

import com.daryl.processor.AnJuKePageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wl
 * @create 2022-02-11
 */
@Component
public class AnJuKePageTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnJuKePageTask.class);

    @Autowired
    private AnJuKePagePipeline anJuKePagePipeline;

    @Autowired
    private AnJuKePageProcessor anJuKePageProcessor;

    private ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    public void crawl() {
        // 定时任务，每10分钟爬取一次
        timer.scheduleWithFixedDelay(() -> {
            Thread.currentThread().setName("anJuKePageCrawlerThread");

            try {
                Spider.create(anJuKePageProcessor)
                        // 从https://www.zhihu.com/explore开始抓
                        .addUrl("https://www.zhihu.com/explore")
                        // 抓取到的数据存数据库
                        .addPipeline(anJuKePagePipeline)
                        // 开启2个线程抓取
                        .thread(2)
                        // 异步启动爬虫
                        .start();
            } catch (Exception ex) {
                LOGGER.error("定时抓取知乎数据线程执行异常", ex);
            }
        }, 0, 10, TimeUnit.MINUTES);
    }

}
