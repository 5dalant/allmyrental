package com.fivedalant.allmyrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AllMyRentalApplication {

    public static void main(String[] args) {

        SpringApplication.run(AllMyRentalApplication.class, args);
    }

}
