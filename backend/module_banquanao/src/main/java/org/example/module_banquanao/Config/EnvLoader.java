package org.example.module_banquanao.Config;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

public class EnvLoader {
    static {
        try {
            Dotenv dotenv = Dotenv.load();
            dotenv.entries().forEach(entry ->
                    System.setProperty(entry.getKey(), entry.getValue())
            );
        } catch (DotenvException e) {
            System.err.println("Error loading .env file: " + e.getMessage());
            System.err.println("Using default system environment variables instead.");
            // Có thể ném ngoại lệ hoặc tiếp tục với biến môi trường hệ thống
            // throw new RuntimeException("Failed to load .env file", e);
        }
    }
}