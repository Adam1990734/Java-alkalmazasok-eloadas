package com.example.javaalkalmazasokeloadas.controller;

import com.example.javaalkalmazasokeloadas.model.temp.TempRequestDto;
import com.example.javaalkalmazasokeloadas.service.TempConverterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class tempConverterController {
    private final TempConverterService tempConverterService;

    public tempConverterController(TempConverterService tempConverterService) {
        this.tempConverterService = tempConverterService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("temp", new TempRequestDto());
        model.addAttribute("availableTempTypes", new String[]{
           "Celsius", "Fahrenheit"
        });
        return "temp/index";
    }

    @PostMapping
    public String convertedResult(
            @ModelAttribute TempRequestDto tempRequestDto,
            Model model
    ) {
        try {
            model.addAttribute(
                    "temp",
                    tempConverterService.convert(tempRequestDto)
            );
            model.addAttribute("error", "");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "temp/result";
    }
}
