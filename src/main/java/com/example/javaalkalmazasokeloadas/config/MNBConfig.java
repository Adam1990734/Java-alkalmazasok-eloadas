package com.example.javaalkalmazasokeloadas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soapMNBClient.MNBArfolyamServiceSoap;
import soapMNBClient.MNBArfolyamServiceSoapImpl;

@Configuration
public class MNBConfig {
    @Bean
    public MNBArfolyamServiceSoap serviceMNB() {
        return new MNBArfolyamServiceSoapImpl().getCustomBindingMNBArfolyamServiceSoap();
    }
}
