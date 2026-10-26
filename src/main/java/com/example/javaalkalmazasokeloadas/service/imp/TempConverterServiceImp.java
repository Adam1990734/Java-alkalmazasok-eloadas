package com.example.javaalkalmazasokeloadas.service.imp;

import com.example.javaalkalmazasokeloadas.model.temp.OperatorNotFoundException;
import com.example.javaalkalmazasokeloadas.model.temp.TempRequestDto;
import com.example.javaalkalmazasokeloadas.model.temp.TempResponseDto;
import com.example.javaalkalmazasokeloadas.service.TempConverterService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TempConverterServiceImp implements TempConverterService {
    @Override
    public Double fahrenheitToCelsius(Double F) {
        return (F - 32) * (5.0/9);
    }

    @Override
    public Double celsiusToFahrenheit(Double C) {
        return C * (9.0/5) + 32;
    }

    @Override
    public String toFahrenheitFormat(Double F) { return String.format("%.2f °F", F); }

    @Override
    public String toCelsiusFormat(Double C) { return String.format("%.2f °C", C); }

    @Override
    public TempResponseDto convert(TempRequestDto dto) {
        switch (dto.getOp()) {
            case TO_CELSIUS:
                return new TempResponseDto(
                        dto.getOp(),
                        fahrenheitToCelsius(dto.getFahrenheit()),
                        dto.getFahrenheit()
                );
            case TO_FAHRENHEIT:
                return new TempResponseDto(
                            dto.getOp(),
                            dto.getCelsius(),
                            celsiusToFahrenheit(dto.getCelsius())
                        );
            default:
                throw new OperatorNotFoundException("No operator exists in this operation!");
        }
    }
}
