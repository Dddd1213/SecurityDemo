package com.yangmao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class TokenApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TokenApplication.class, args);
        System.out.println(run);
    }
}
