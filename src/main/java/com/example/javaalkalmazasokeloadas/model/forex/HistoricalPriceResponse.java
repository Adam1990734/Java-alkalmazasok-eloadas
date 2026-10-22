package com.example.javaalkalmazasokeloadas.model.forex;

import com.oanda.primitives.DateTime;

public class HistoricalPriceResponse {
    private DateTime dateTime;
    private String name;
    private Double mid;

    public HistoricalPriceResponse() { }

    public HistoricalPriceResponse(DateTime dateTime, String name, Double mid) {
        this.dateTime = dateTime;
        this.name = name;
        this.mid = mid;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }
}
