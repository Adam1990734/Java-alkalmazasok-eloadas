package com.example.javaalkalmazasokeloadas.controller.forex;

import com.example.javaalkalmazasokeloadas.model.forex.AccountInfo;
import com.example.javaalkalmazasokeloadas.model.forex.Instrument.InstrumentRequest;
import com.example.javaalkalmazasokeloadas.model.forex.Position.*;
import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import com.oanda.ExecuteException;
import com.oanda.RequestException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.oanda.Context;
import java.util.List;

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
    public String index(Model model) throws ExecuteException, RequestException {
        var info = context.account.summary(
                apiKeysService.getAccountId()
        ).getAccount();
        model.addAttribute("accountInfo", new AccountInfo(
                info.getBalance(),
                info.getWithdrawalLimit(),
                info.getOpenPositionCount(),
                info.getOpenTradeCount(),
                info.getPendingOrderCount()
        ));
        return "forex/index";
    }
}
