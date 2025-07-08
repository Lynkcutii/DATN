package org.example.module_banquanao;

import org.example.module_banquanao.Config.EnvLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
public class ModuleBanquanaoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        new EnvLoader();
        SpringApplication.run(ModuleBanquanaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("password123");
        System.out.println("Encoded Password: " + encodedPassword);
    }
}