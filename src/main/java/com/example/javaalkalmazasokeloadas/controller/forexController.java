package com.example.javaalkalmazasokeloadas.controller;

import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import com.oanda.account.AccountSummary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oanda.Context;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/forex")
public class forexController {
    private final Context context;
    private final ApiKeysService apiKeysService;

    public forexController(ApiKeysService apiKeysService, Context context) {
        this.apiKeysService = apiKeysService;
        this.context = context;
    }

    @GetMapping
    @ResponseBody
    public AccountSummary Valami() {
        try {
            AccountSummary summary = context.account.summary(apiKeysService.getAccountId()).getAccount();
            return summary;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
