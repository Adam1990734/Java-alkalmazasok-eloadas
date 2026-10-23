package com.example.javaalkalmazasokeloadas.model.forex.Position;

import com.oanda.trade.TradeID;

public class ClosePositionRequest {
    private TradeID id;

    public ClosePositionRequest() {}

    public ClosePositionRequest(TradeID id) {
        this.id = id;
    }

    public TradeID getId() {
        return id;
    }

    public void setId(TradeID id) {
        this.id = id;
    }
}
