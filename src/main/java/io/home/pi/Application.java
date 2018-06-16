package io.home.pi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi
 * USER      : sean
 * DATE      : 12-June-2018
 * TIME      : 21:32
 */
@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        // Launch the application
        LOGGER.info("LAUNCHING...");
        SpringApplication.run(Application.class, args);
        LOGGER.info("LAUNCHING SEQUENCE COMPLETED!");
    }
}
