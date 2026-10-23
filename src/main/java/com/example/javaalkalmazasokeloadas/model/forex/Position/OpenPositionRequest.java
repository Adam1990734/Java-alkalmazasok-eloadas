package com.example.javaalkalmazasokeloadas.model.forex.Position;

public class OpenPositionRequest {
    private String name;
    private Double unit;

    public OpenPositionRequest() {}

    public OpenPositionRequest(String name, Double unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replace("/", "_");
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }
}
