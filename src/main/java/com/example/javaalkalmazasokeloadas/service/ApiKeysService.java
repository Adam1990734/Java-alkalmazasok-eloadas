package com.example.javaalkalmazasokeloadas.service;

import com.oanda.account.AccountID;
import org.springframework.stereotype.Service;

@Service
public interface ApiKeysService {
    String getForexAccountId();
    String getForexToken();
    String getUrl();
    AccountID getAccountId();
}
