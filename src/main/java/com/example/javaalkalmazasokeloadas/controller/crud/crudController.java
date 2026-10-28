package com.example.javaalkalmazasokeloadas.controller.crud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crud")
public class crudController {
    @RequestMapping({
            "/",
            "/**"
    })
    public String index() {
        return "forward:/react/index.html";
    }
}
