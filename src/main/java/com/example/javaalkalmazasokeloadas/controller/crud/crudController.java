package com.example.javaalkalmazasokeloadas.controller.crud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crud")
public class crudController {
    @GetMapping
    public String index() {
        //Ez lesz majd a reactot küldeni
        return "crud/";
    }
}
