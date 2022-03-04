package com.daryl.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wl
 * @create 2022-02-09
 */
@Component
public class DataRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataRunner.class);

    @Override
    public void run(String... args) {
        LOGGER.info("DARYL FOR EVER ---> House Finished...");
    }
}
