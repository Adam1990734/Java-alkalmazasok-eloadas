package com.example.javaalkalmazasokeloadas;

import com.example.javaalkalmazasokeloadas.service.TempConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(tempControllerTest.class)
@Import(TempConverterService.class)
public class tempControllerTest {
    @Autowired
    private MockMvc mockMvc;

}
