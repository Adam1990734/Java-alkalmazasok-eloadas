package com.example.javaalkalmazasokeloadas;

import com.example.javaalkalmazasokeloadas.controller.tempConverterController;
import com.example.javaalkalmazasokeloadas.service.TempConverterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(tempConverterController.class)
public class tempControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TempConverterService tempConverterService;

    @Test
    @DisplayName("GET - temp, default loading check")
    void testTempGET() throws Exception {
        mockMvc.perform(get("/temp"))
                .andExpect(status().isOk())
                .andExpect(view().name("temp/index"));
    }

    @Test
    @DisplayName("POST - temp, default use case result")
    void testTempPOSTDefaultUseCase() throws Exception {
        mockMvc.perform(post("/temp")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("temp1Name", "Celsius")
                        .param("temp1Value", "100")
                        .param("temp2Name", "Fahrenheit"))
                .andExpect(status().isOk())
                .andExpect(view().name("temp/result"));
    }

    @Test
    @DisplayName("POST - temp, error attribute check")
    void testTempPOSTErrorHandling() throws Exception {
        mockMvc.perform(post("/temp")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("temp1Name", "Celsius")
                        .param("temp1Value", "100")
                        .param("temp2Name", "Celsius"))
                .andExpect(status().isOk())
                .andExpect(view().name("temp/result"));
    }
}