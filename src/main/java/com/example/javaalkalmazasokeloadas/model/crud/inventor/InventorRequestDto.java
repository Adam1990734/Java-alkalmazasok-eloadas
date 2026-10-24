package com.example.javaalkalmazasokeloadas.model.crud.inventor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class InventorRequestDto {
    @NotBlank
    @Size(max = 40)
    private String name;

    @NotNull
    private Integer bornat;

    private Integer diedat;

    @NotEmpty
    private Set<Long> inventions;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBornat() {
        return bornat;
    }
    public void setBornat(Integer bornat) {
        this.bornat = bornat;
    }
    public Integer getDiedat() {
        return diedat;
    }
    public void setDiedat(Integer diedat) {
        this.diedat = diedat;
    }

    public Set<Long> getInventions() {
        return inventions;
    }

    public void setInventions(Set<Long> inventions) {
        this.inventions = inventions;
    }
}
