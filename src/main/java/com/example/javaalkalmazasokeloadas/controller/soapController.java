package com.example.javaalkalmazasokeloadas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import soapMNBClient.MNBArfolyamServiceSoap;
import soapMNBClient.MNBArfolyamServiceSoapGetCurrentExchangeRatesStringFaultFaultMessage;
import soapMNBClient.MNBArfolyamServiceSoapGetInfoStringFaultFaultMessage;
import soapMNBClient.MNBArfolyamServiceSoapImpl;

import java.time.LocalDate;

@Controller
@RequestMapping("/soap")
public class soapController {
    private MNBArfolyamServiceSoap soapService;

    public soapController(MNBArfolyamServiceSoap soapService) {
        this.soapService = soapService;
    }

    @GetMapping
    public String index(Model model) {
        return "MNBView/index";
    }
    @GetMapping("/search")
    @ResponseBody
    public String search(
            @RequestParam(name = "Currancy", required = true) String Currancy,
            @RequestParam(name = "startDate", required = true) LocalDate startDate,
            @RequestParam(name = "endDate", required = true) LocalDate endDate,
            Model model
    ) throws MNBArfolyamServiceSoapGetCurrentExchangeRatesStringFaultFaultMessage, MNBArfolyamServiceSoapGetInfoStringFaultFaultMessage {
        return soapService.getInfo() + "<br>" + soapService.getCurrentExchangeRates();
    }
}
