package com.example.javaalkalmazasokeloadas.model.forex.Position;

import com.oanda.trade.TradeID;

public class ClosePositionResponse {
    private TradeID id;

    public ClosePositionResponse() {}

    public ClosePositionResponse(TradeID id) {
        this.id = id;
    }

    public TradeID getId() {
        return id;
    }

    public void setId(TradeID id) {
        this.id = id;
    }
}
