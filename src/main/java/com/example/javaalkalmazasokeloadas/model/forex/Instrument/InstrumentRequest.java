package com.example.javaalkalmazasokeloadas.model.forex.Instrument;

import com.oanda.instrument.CandlestickGranularity;

public class InstrumentRequest {
    private String name;
    private CandlestickGranularity granularity;

    public InstrumentRequest() {}
    public InstrumentRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replace("/", "_");
    }

    public CandlestickGranularity getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        switch (granularity) {
            case "M1": this.granularity = CandlestickGranularity.M1; break;
            case "H1": this.granularity = CandlestickGranularity.H1; break;
            case "D": this.granularity = CandlestickGranularity.D; break;
            case "W": this.granularity = CandlestickGranularity.W; break;
            case "M": this.granularity = CandlestickGranularity.M; break;
        }
    }

    public void setGranularity(CandlestickGranularity granularity) {
        this.granularity = granularity;
    }
}
