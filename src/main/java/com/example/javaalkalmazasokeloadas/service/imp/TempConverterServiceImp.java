package com.example.javaalkalmazasokeloadas.service.imp;

import com.example.javaalkalmazasokeloadas.model.temp.OperatorNotFoundException;
import com.example.javaalkalmazasokeloadas.model.temp.TempRequestDto;
import com.example.javaalkalmazasokeloadas.model.temp.TempResponseDto;
import com.example.javaalkalmazasokeloadas.service.TempConverterService;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        String temp1Value = "", temp2Value = "";
        if(Objects.equals(dto.getTemp1Name(), "Celsius") && Objects.equals(dto.getTemp2Name(), "Fahrenheit")) {
            temp1Value = toCelsiusFormat(dto.getTemp1Value());
            temp2Value = toFahrenheitFormat(
                    celsiusToFahrenheit(dto.getTemp1Value())
            );
        }
        else if(Objects.equals(dto.getTemp2Name(), "Celsius") && Objects.equals(dto.getTemp1Name(), "Fahrenheit")) {
            temp1Value = toFahrenheitFormat(dto.getTemp1Value());
            temp2Value = toCelsiusFormat(
                    celsiusToFahrenheit(dto.getTemp1Value())
            );
        } else
            throw new OperatorNotFoundException("No operator exists in this operation!");
        return new TempResponseDto(
                dto.getTemp1Name(),
                temp1Value,
                dto.getTemp2Name(),
                temp2Value
        );
    }
}
