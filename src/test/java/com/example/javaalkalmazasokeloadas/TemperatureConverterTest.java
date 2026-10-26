package com.example.javaalkalmazasokeloadas;

import com.example.javaalkalmazasokeloadas.service.TempConverterService;
import com.example.javaalkalmazasokeloadas.service.imp.TempConverterServiceImp;
import org.junit.jupiter.api.BeforeEach;

public class TemperatureConverterTest {
    private TempConverterService tempConverterService;

    @BeforeEach
    void SetUp() {
        this.tempConverterService = new TempConverterServiceImp();
    }

}
