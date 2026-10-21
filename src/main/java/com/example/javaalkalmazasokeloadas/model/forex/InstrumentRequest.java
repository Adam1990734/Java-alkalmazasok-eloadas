package com.example.javaalkalmazasokeloadas.model.forex;

public class InstrumentRequest {
    private String name;

    public InstrumentRequest() {}
    public InstrumentRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
