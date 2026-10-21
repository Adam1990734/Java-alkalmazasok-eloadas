package com.example.javaalkalmazasokeloadas.service;

import org.springframework.stereotype.Service;

@Service
public interface ApiKeysService {
    String getForexAccountId();
    String getForexToken();
}
