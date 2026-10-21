package com.example.javaalkalmazasokeloadas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.javaalkalmazasokeloadas.service.ApiKeysService;

@Controller
@RequestMapping("/forex")
public class forexController {
    private final ApiKeysService apiKeysService;

    public forexController(final ApiKeysService apiKeysService) {
        this.apiKeysService = apiKeysService;
    }

    @GetMapping
    @ResponseBody
    public String Valami() {
        return apiKeysService.getForexAccountId();
    }
}
