package com.encore.petandbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PetandbeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetandbeApplication.class, args);
    }

}
