package com.example.javaalkalmazasokeloadas;

import com.example.javaalkalmazasokeloadas.model.temp.OperatorNotFoundException;
import com.example.javaalkalmazasokeloadas.model.temp.SameTypesGivenException;
import com.example.javaalkalmazasokeloadas.model.temp.TempRequestDto;
import com.example.javaalkalmazasokeloadas.service.TempConverterService;
import com.example.javaalkalmazasokeloadas.service.imp.TempConverterServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {
    private TempConverterService tempConverterService;

    @BeforeEach
    void SetUp() {
        this.tempConverterService = new TempConverterServiceImp();
    }

    @Test
    @DisplayName("Testing Fahrenheit to Celsius conversion")
    void testFahrenheitToCelsius() {
        assertEquals(
                0,
                tempConverterService.fahrenheitToCelsius(32.0)
        );
    }

    @Test
    @DisplayName("Testing Fahrenheit to Celsius conversion")
    void testCelsiusToFahrenheit() {
        assertEquals(
                212,
                tempConverterService.celsiusToFahrenheit(100.0)
        );
    }

    @Test
    @DisplayName("Testing Fahrenheit value format with Integer")
    void testFormatFahrenheitWithInteger() {
        assertEquals(
                "32,00 °F",
                tempConverterService.toFahrenheitFormat(32.0)
        );
    }

    @Test
    @DisplayName("Testing Fahrenheit value format with Double")
    void testFormatFahrenheitWithDouble() {
        assertEquals(
                "34,25 °F",
                tempConverterService.toFahrenheitFormat(34.245)
        );
    }

    @Test
    @DisplayName("Testing Celsius value format with Integer")
    void testFormatCelsiusWithInteger() {
        assertEquals(
                "12,00 °C",
                tempConverterService.toCelsiusFormat(12.0)
        );
    }

    @Test
    @DisplayName("Testing Celsius value format with Double")
    void testFormatCelsiusWithDouble() {
        assertEquals(
                "12,85 °C",
                tempConverterService.toCelsiusFormat(12.8514)
        );
    }

    @Test
    @DisplayName("Testing response handling with correct data")
    void testResponseSimpleCase() {
        var request = new TempRequestDto();
        request.setTemp1Name("Celsius");
        request.setTemp1Value(100.0);
        request.setTemp2Name("Fahrenheit");

        var result = tempConverterService.convert(request);

        assertEquals(
                "Celsius",
                result.getTemp1Name()
        );
        assertEquals(
                "100,00 °C",
                result.getTemp1Value()
        );
        assertEquals(
                "Fahrenheit",
                result.getTemp2Name()
        );
        assertEquals(
                "212,00 °F",
                result.getTemp2Value()
        );
    }

    @Test
    @DisplayName("Testing response with same parameter, should be illegal")
    void testResponseWithSameParameter() {
        var request = new TempRequestDto();
        request.setTemp1Name("Fahrenheit");
        request.setTemp1Value(100.0);
        request.setTemp2Name("Fahrenheit");

        assertThrowsExactly(SameTypesGivenException.class, () -> {
            var result = tempConverterService.convert(request);
        });
    }

    @Test
    @DisplayName("Testing response with a not correct operation or not defined, should be illegal")
    void testResponseWithBadOperator() {
        var request = new TempRequestDto();
        request.setTemp1Name("NemLetezo");
        request.setTemp1Value(100.0);
        request.setTemp2Name("Fahrenheit");

        assertThrowsExactly(OperatorNotFoundException.class, () -> {
            var result = tempConverterService.convert(request);
        });
    }
}
