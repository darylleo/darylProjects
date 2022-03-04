package com.daryl.paperline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


/**
 * @author wl
 * @create 2022-02-11
 */
@Component
public class AnJuKePagePipeline implements Pipeline {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnJuKePagePipeline.class);


    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String answer = resultItems.get("answer");


        try {
            System.out.println(title);
            System.out.println(answer);
            LOGGER.info("保存知乎文章成功：{}", title);
        } catch (Exception ex) {
            LOGGER.error("保存知乎文章失败", ex);
        }
    }

}
