package com.example.javaalkalmazasokeloadas.config;

import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.oanda.Context;

@Configuration
public class forexConfig {
    private final ApiKeysService apiKeysService;

    public forexConfig(ApiKeysService apiKeysService) {
        this.apiKeysService = apiKeysService;
    }

    @Bean
    public Context serviceContext() {
        return new Context(
                apiKeysService.getUrl(),
                apiKeysService.getForexToken()
        );
    }
}
