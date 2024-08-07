package com.tinqinacademy.bff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@EnableJdbcRepositories(basePackages = "com.tinqinacademy.hotel.persistence.repository.interfaces")
//@EnableJpaRepositories(basePackages = "com.tinqinacademy.hotel.persistence.repository.interfaces")
//@EntityScan(basePackages = "com.tinqinacademy.hotel.persistence.entities")
public class BffApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffApplication.class, args);
    }

}
