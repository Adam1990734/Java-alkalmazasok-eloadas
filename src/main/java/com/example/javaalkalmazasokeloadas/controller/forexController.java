package com.example.javaalkalmazasokeloadas.controller;

import com.example.javaalkalmazasokeloadas.model.forex.AccountInfo;
import com.example.javaalkalmazasokeloadas.model.forex.InstrumentRequest;
import com.example.javaalkalmazasokeloadas.model.forex.InstrumentResponse;
import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import com.oanda.ExecuteException;
import com.oanda.RequestException;
import com.oanda.account.AccountInstrumentsRequest;
import com.oanda.account.AccountSummary;
import com.oanda.pricing.PricingGetRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.oanda.Context;

import java.util.ArrayList;
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
        model.addAttribute("", new AccountInfo(
                info.getBalance(),
                info.getWithdrawalLimit(),
                info.getOpenPositionCount(),
                info.getOpenTradeCount(),
                info.getPendingOrderCount()
        ));
        return "forex/index";
    }

    @GetMapping("/instruments")
    public String selectInstruments(
            @RequestParam(name = "len", defaultValue = "50") Integer len,
            Model model
    ) throws ExecuteException, RequestException {
        var request = new AccountInstrumentsRequest(apiKeysService.getAccountId());
        var info = context.account.instruments(request);

        var instruments = new ArrayList<String>();
        for(var instrument : info.getInstruments()) {
            instruments.add(instrument.getDisplayName());
            if(instruments.size() == len)
                break;
        }
        model.addAttribute("", instruments);
        model.addAttribute("instrument", new InstrumentRequest());
        return "forex/instruments";
    }

    @PostMapping("/actprice")
    public String actualPrice(
            @ModelAttribute InstrumentRequest instrumentRequest,
            Model model
    ) throws ExecuteException, RequestException {
        var request = new PricingGetRequest(
                apiKeysService.getAccountId(),
                List.of(instrumentRequest.getName())
        );
        var info = context.pricing.get(request);
        //Ezt be kell fejezni!
        return "forex/actprice";
    }
}
