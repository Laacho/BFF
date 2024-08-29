package com.tinqinacademy.bff.domain.config.factory;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientFactory {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
