package com.engine;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.engine")
@ConfigurationPropertiesScan(basePackages = "com.engine")
@EnableJpaRepositories(basePackages = "com.engine")
@EntityScan(basePackages = "com.engine")
@EnableCaching
public class App {
    public static void main(String[] args) { SpringApplication.run(App.class, args); }
}