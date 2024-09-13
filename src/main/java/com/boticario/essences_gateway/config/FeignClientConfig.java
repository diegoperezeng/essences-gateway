package com.boticario.essences_gateway.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class FeignClientConfig {

    @Value("${essences.basic.user}")
    private String essenceUser;

    @Value("${essences.basic.password}")
    private String essencePassword;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                String auth = essenceUser + ":" + essencePassword;
                String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
                requestTemplate.header("Authorization", "Basic " + encodedAuth);
            }
        };
    }

}