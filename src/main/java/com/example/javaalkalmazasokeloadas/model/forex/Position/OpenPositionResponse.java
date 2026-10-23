package com.example.javaalkalmazasokeloadas.model.forex.Position;

import com.oanda.transaction.TransactionID;

public class OpenPositionResponse {
    private TransactionID id;
    private String name;
    private Double unit;

    public OpenPositionResponse() {}
    public OpenPositionResponse(TransactionID id, String name, Double unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public TransactionID getId() {
        return id;
    }

    public void setId(TransactionID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }
}
