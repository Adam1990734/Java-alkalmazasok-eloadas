package com.example.javaalkalmazasokeloadas.model.forex;

public class InstrumentResponse extends InstrumentRequest {
    private Double Price;

    public InstrumentResponse() { super(); }

    public InstrumentResponse(String name, Double price) {
        super(name);
        Price = price;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
