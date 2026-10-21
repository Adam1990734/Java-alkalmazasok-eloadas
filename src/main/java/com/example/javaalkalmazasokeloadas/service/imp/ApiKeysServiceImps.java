package com.example.javaalkalmazasokeloadas.service.imp;
import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
public class ApiKeysServiceImps implements ApiKeysService {
    @Value("${forex.account-id}")
    private String accountId;
    @Value("${forex.token}")
    private String token;

    @Override
    public String getForexAccountId() { return accountId; }

    @Override
    public String getForexToken() { return token; }
}
