package com.example.javaalkalmazasokeloadas.controller;

import com.example.javaalkalmazasokeloadas.model.mnb.CurrencyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import soapMNBClient.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/soap")
public class soapController {
    private MNBArfolyamServiceSoap soapService;

    public soapController(MNBArfolyamServiceSoap soapService) {
        this.soapService = soapService;
    }

    public List<String> currencyXmlToList(String currenciesXML) {
        Pattern pattern = Pattern.compile("<Curr>(.*?)</Curr>");
        Matcher matcher = pattern.matcher(currenciesXML);
        List<String> currencies = new ArrayList<>();
        while (matcher.find())
            currencies.add(matcher.group(1));
        return currencies;
    }

    @GetMapping
    public String index(Model model) throws MNBArfolyamServiceSoapGetCurrenciesStringFaultFaultMessage {
        var currencies = currencyXmlToList(soapService.getCurrencies());
        model.addAttribute("currency", new CurrencyData());
        model.addAttribute("availableCurrencies", currencies);
        return "MNBView/index";
    }
    @GetMapping("/search")
    public String search(
            @ModelAttribute CurrencyData currencyData,
            Model model
    ) throws MNBArfolyamServiceSoapGetExchangeRatesStringFaultFaultMessage {
        var currencyRate = soapService.getExchangeRates(
                currencyData.getStartDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                currencyData.getEndDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")),
                currencyData.getName()
        );
        //Ez jó de még a kimenet html és diagramm kell és persze az xml-ből rendes típus kéne
        model.addAttribute("currancyRate", currencyData);
        return "MNBView/result";
    }
}
