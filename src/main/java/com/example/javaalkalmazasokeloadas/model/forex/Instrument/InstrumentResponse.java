package com.example.javaalkalmazasokeloadas.model.forex.Instrument;

public class InstrumentResponse {
    private String name;
    private Double bid;
    private Double ask;

    public InstrumentResponse() { }

    public InstrumentResponse(String name, Double bid, Double ask) {
        this.name = name;
        this.bid = bid;
        this.ask = ask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }
}
