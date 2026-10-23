package com.example.javaalkalmazasokeloadas.model.forex.Position;

import com.oanda.primitives.DateTime;
import com.oanda.trade.TradeID;

public class OpenedPositionResponse {
    private TradeID id;
    private DateTime openedAt;
    private String name;

    private Double initUnit;
    private Double currentUnit;
    private Double price;
    private Double unrealizedPL;

    public OpenedPositionResponse() {}

    public OpenedPositionResponse(TradeID id, DateTime openedAt, String name, Double initUnit, Double currentUnit, Double price, Double unrealizedPL) {
        this.id = id;
        this.openedAt = openedAt;
        this.name = name;
        this.initUnit = initUnit;
        this.currentUnit = currentUnit;
        this.price = price;
        this.unrealizedPL = unrealizedPL;
    }

    public TradeID getId() {
        return id;
    }

    public void setId(TradeID id) {
        this.id = id;
    }

    public DateTime getOpenedAt() {
        return openedAt;
    }

    public void setOpenedAt(DateTime openedAt) {
        this.openedAt = openedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getInitUnit() {
        return initUnit;
    }

    public void setInitUnit(Double initUnit) {
        this.initUnit = initUnit;
    }

    public Double getCurrentUnit() {
        return currentUnit;
    }

    public void setCurrentUnit(Double currentUnit) {
        this.currentUnit = currentUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getUnrealizedPL() {
        return unrealizedPL;
    }

    public void setUnrealizedPL(Double unrealizedPL) {
        this.unrealizedPL = unrealizedPL;
    }
}
