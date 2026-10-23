package com.example.javaalkalmazasokeloadas.controller.forex;

import com.example.javaalkalmazasokeloadas.model.forex.Instrument.InstrumentRequest;
import com.example.javaalkalmazasokeloadas.service.InstrumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/forex/instruments")
public class instrumentController {
    private final InstrumentService instrumentService;

    public instrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public String index(
            @RequestParam(name = "len", defaultValue = "50") Long len,
            Model model
    ) {
        var instruments = instrumentService.getNames(len);
        if(instruments.isPresent()) {
            model.addAttribute("availableInstruments", instruments.get());
            model.addAttribute("availableGranularities", List.of(
                    "M1",
                    "H1",
                    "D",
                    "W",
                    "M"
            ));
            model.addAttribute("chosenInstrument", new InstrumentRequest());
            return "forex/instruments";
        } else return "redirect:/forex/";
    }

    @PostMapping("/actprice")
    public String actualPrice(
            @ModelAttribute InstrumentRequest instrumentRequest,
            Model model
    ) {
        var instrument = instrumentService.getCurrentPrice(instrumentRequest);
        if(instrument.isPresent()) {
            model.addAttribute("price", instrument.get());
            return "forex/actprice";
        } else return "redirect:/forex/instruments";
    }

    @PostMapping("/histprice")
    public String historical(
            @ModelAttribute InstrumentRequest instrumentRequest,
            Model model
    ) {
        var instruments = instrumentService.getHistoricals(instrumentRequest, 10L);
        if(instruments.isPresent()) {
            model.addAttribute("prices", instruments.get());
            return "forex/histprice";
        } else return "redirect:/forex/instruments";
    }
}
