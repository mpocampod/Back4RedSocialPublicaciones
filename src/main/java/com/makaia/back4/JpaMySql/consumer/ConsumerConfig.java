package com.makaia.back4.JpaMySql.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConsumerConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
