package com.fintech.jjeondaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JjeondaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JjeondaProjectApplication.class, args);
    }

}
