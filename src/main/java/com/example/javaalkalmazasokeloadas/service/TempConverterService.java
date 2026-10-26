package com.example.javaalkalmazasokeloadas.service;

import com.example.javaalkalmazasokeloadas.model.temp.TempRequestDto;
import com.example.javaalkalmazasokeloadas.model.temp.TempResponseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TempConverterService {
    Double fahrenheitToCelsius(Double F);
    Double celsiusToFahrenheit(Double C);
    String toFahrenheitFormat(Double F);
    String toCelsiusFormat(Double C);

    TempResponseDto convert(TempRequestDto dto);
}
