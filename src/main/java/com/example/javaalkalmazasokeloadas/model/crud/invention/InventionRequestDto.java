package com.example.javaalkalmazasokeloadas.model.crud.invention;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InventionRequestDto {
    @NotBlank
    @Size(max = 80)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
