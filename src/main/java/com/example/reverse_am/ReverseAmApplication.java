package com.example.reverse_am;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ReverseAmApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReverseAmApplication.class, args);
    }
}
