package com.example.javaalkalmazasokeloadas.controller;

import com.example.javaalkalmazasokeloadas.model.mnb.CurrencyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import soapMNBClient.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map<LocalDate, Float> currencyRateXmlToLookUp(String currencyRateXML) {
        Pattern datePattern = Pattern.compile("<Day date=\"(.*?)\">");
        Matcher dateMacher = datePattern.matcher(currencyRateXML);
        Pattern ratePattern = Pattern.compile(">([0-9]*,[0-9]*)</Rate>");
        Matcher rateMacher = ratePattern.matcher(currencyRateXML);
        Map<LocalDate, Float> map = new HashMap<>();
        while(dateMacher.find() && rateMacher.find()) {
            var date = LocalDate.parse(dateMacher.group(1), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            var rate = Float.parseFloat(rateMacher.group(1).replace(',', '.'));
            map.put(date, rate);
        }
        return map;
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
        var result = currencyRateXmlToLookUp(currencyRate);
        var points = result.entrySet()
                .stream()
                .map(e -> Map.of(
                        "x", e.getKey().toString(),
                        "y", e.getValue())).toList();
        model.addAttribute("currencyName", currencyData.getName());
        model.addAttribute("points", points);
        return "MNBView/result";
    }
}
