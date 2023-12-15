package com.privatepro06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Privatepro06Application {

    public static void main(String[] args) {
        SpringApplication.run(Privatepro06Application.class, args);
    }

}
