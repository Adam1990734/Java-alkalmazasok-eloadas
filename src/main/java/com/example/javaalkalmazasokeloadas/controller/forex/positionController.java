package com.example.javaalkalmazasokeloadas.controller.forex;

import com.example.javaalkalmazasokeloadas.model.forex.Position.ClosePositionRequest;
import com.example.javaalkalmazasokeloadas.model.forex.Position.OpenPositionRequest;
import com.example.javaalkalmazasokeloadas.service.InstrumentService;
import com.example.javaalkalmazasokeloadas.service.PositionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/forex/positions")
public class positionController {
    private final PositionService positionService;
    private final InstrumentService instrumentService;

    public positionController(PositionService positionService, InstrumentService instrumentService) {
        this.positionService = positionService;
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
            model.addAttribute("chosenInstrumentToOpen", new OpenPositionRequest());
            model.addAttribute("chosenTradeIdToClose", new ClosePositionRequest());
            return "forex/position/index";
        } else return "redirect:/forex";
    }

    @GetMapping("/openedpos")
    public String openedPositions(
            @RequestParam(name = "len", defaultValue = "50") Long len,
            Model model
    ) {
        var positions = positionService.getAll(len);
        if(positions.isPresent()) {
            model.addAttribute("positions", positions.get());
            return "forex/position/openedpos";
        } else return "redirect:/forex/positions";
    }

    @PostMapping("/openpos")
    public String openPosition(
            @ModelAttribute OpenPositionRequest openPositionRequest,
            Model model
    ) {
        var position = positionService.open(openPositionRequest);
        if(position.isPresent()) {
            model.addAttribute("position", position.get());
            return "forex/position/openpos";
        } else return "redirect:/forex/positions";
    }

    @PostMapping("/closepos")
    public String closePosition(
            @ModelAttribute ClosePositionRequest closePositionRequest,
            Model model
    ) {
        var position = positionService.close(closePositionRequest);
        if(position.isPresent()) {
            model.addAttribute("position", position.get());
            return "forex/position/closepos";
        } else return "redirect:/forex/positions";
    }
}
