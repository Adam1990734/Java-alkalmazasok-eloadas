package com.example.javaalkalmazasokeloadas.model.crud.invention;

import com.example.javaalkalmazasokeloadas.model.crud.inventor.InventorShortDto;

import java.util.Set;

public class InventionResponseDto {
    private Long id;
    private String name;
    private Set<InventorShortDto> inventors;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<InventorShortDto> getInventors() {
        return inventors;
    }
    public void setInventors(Set<InventorShortDto> inventorIds) {
        this.inventors = inventorIds;
    }
}
