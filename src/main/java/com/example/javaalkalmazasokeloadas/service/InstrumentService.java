package com.example.javaalkalmazasokeloadas.service;

import com.example.javaalkalmazasokeloadas.model.forex.HistoricalPriceResponse;
import com.example.javaalkalmazasokeloadas.model.forex.Instrument.InstrumentRequest;
import com.example.javaalkalmazasokeloadas.model.forex.Instrument.InstrumentResponse;
import com.oanda.ExecuteException;
import com.oanda.RequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InstrumentService {
    Optional<List<String>> getNames(Long count);
    Optional<InstrumentResponse> getCurrentPrice(InstrumentRequest instrumentRequest);
    Optional<List<HistoricalPriceResponse>> getHistoricals(InstrumentRequest instrumentRequest, Long count);
}
