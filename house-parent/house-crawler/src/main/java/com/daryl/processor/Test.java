package com.daryl.processor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

/**
 * @author wl
 * @create 2022-02-11
 */
public class Test {
    public static void main(String[] args) {
        Spider.create(new AnJuKePageProcessor())
                .addUrl("https://github.com/code4craft")
                .addPipeline(new JsonFilePipeline("D:\\AnJuKeHouseInfo\\"))
                .thread(5)
                .run();
    }
}
