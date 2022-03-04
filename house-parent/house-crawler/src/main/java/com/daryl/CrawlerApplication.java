package com.daryl;

import com.daryl.config.AnJuKePageTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wl
 * @create 2022-02-11
 */
@SpringBootApplication
@EnableScheduling
public class CrawlerApplication implements CommandLineRunner {

    @Autowired
    private AnJuKePageTask anJuKePageTask;

    public static void main(String[] args)  {
        SpringApplication.run(CrawlerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // 爬取知乎数据
        anJuKePageTask.crawl();

    }
}