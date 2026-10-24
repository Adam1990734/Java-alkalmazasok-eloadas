package com.example.javaalkalmazasokeloadas.model.crud.invention;

public class InventionShortDto {
    private Long id;
    private String Name;

    public InventionShortDto() {}
    public InventionShortDto(Long id, String name) {
        this.id = id;
        Name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
