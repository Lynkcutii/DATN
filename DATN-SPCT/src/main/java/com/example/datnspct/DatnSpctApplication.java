package com.example.datnspct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.datnspct.Model")
public class DatnSpctApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatnSpctApplication.class, args);
    }

}
