package com.daryl.processor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * 安居客页面爬虫处理器
 * @author wl
 * @create 2022-02-11
 */
@Component
public class AnJuKePageProcessor implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    //解析页面
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("https://www\\.zhihu\\.com/question/\\d+/answer/\\d+.*").all());
        page.putField("title", page.getHtml().xpath("//h1[@class='QuestionHeader-title']/text()").toString());
        page.putField("answer", page.getHtml().xpath("//div[@class='QuestionAnswer-content']/tidyText()").toString());
        if (page.getResultItems().get("title") == null) {
            // 如果是列表页，跳过此页，pipeline不进行后续处理
            page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    //initialDelay 当任务启动后，等待多久执行方法
    //fixedDelay 每隔多久执行方法
    @Scheduled(initialDelay = 1000,fixedDelay = 100*1000)
    public void process(){

        Spider.create(new AnJuKePageProcessor())
                .addUrl("")
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                .run();
    }


}
