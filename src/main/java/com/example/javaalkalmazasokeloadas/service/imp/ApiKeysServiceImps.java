package com.example.javaalkalmazasokeloadas.service.imp;
import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import com.oanda.account.AccountID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiKeysServiceImps implements ApiKeysService {
    @Value("${forex.account-id}")
    private String accountId;
    @Value("${forex.token}")
    private String token;
    @Value("${forex.url}")
    private String url;

    @Override
    public String getForexAccountId() { return accountId; }

    @Override
    public String getForexToken() { return token; }

    @Override
    public String getUrl() { return url; }

    @Override
    public AccountID getAccountId() {
        return new AccountID(this.accountId);
    }
}
