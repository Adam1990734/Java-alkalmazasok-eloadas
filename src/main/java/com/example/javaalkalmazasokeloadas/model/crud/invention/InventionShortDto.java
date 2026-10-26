package com.example.javaalkalmazasokeloadas.model.crud.invention;

public class InventionShortDto {
    private Long id;
    private String name;

    public InventionShortDto() {}
    public InventionShortDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
