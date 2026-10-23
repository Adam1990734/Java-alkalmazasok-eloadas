package com.example.javaalkalmazasokeloadas.service.imp;

import com.example.javaalkalmazasokeloadas.model.forex.HistoricalPriceResponse;
import com.example.javaalkalmazasokeloadas.model.forex.Instrument.InstrumentRequest;
import com.example.javaalkalmazasokeloadas.model.forex.Instrument.InstrumentResponse;
import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import com.example.javaalkalmazasokeloadas.service.InstrumentService;
import com.oanda.Context;
import com.oanda.ExecuteException;
import com.oanda.RequestException;
import com.oanda.account.AccountInstrumentsRequest;
import com.oanda.instrument.InstrumentCandlesRequest;
import com.oanda.pricing.PricingGetRequest;
import com.oanda.primitives.Instrument;
import com.oanda.primitives.InstrumentName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstrumentServiceImp implements InstrumentService {
    private final ApiKeysService apiKeysService;
    private final Context context;

    public InstrumentServiceImp(Context context, ApiKeysService apiKeysService) {
        this.context = context;
        this.apiKeysService = apiKeysService;
    }

    @Override
    public Optional<List<String>> getNames(Long count) {
        try {
            var request = new AccountInstrumentsRequest(apiKeysService.getAccountId());
            var resp = context.account.instruments(request);
            return Optional.of(
                    resp.getInstruments().stream().map(Instrument::getDisplayName)
                            .limit(count)
                            .toList()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<InstrumentResponse> getCurrentPrice(InstrumentRequest instrumentRequest) {
        try {
            var request = new PricingGetRequest(
                    apiKeysService.getAccountId(),
                    List.of(instrumentRequest.getName())
            );

            var resp = context.pricing.get(request);
            return Optional.of(
                    resp.getPrices().stream().map(price ->
                        new InstrumentResponse(
                                instrumentRequest.getName(),
                                resp.getPrices().getFirst()
                                        .getBids()
                                        .getFirst()
                                        .getPrice()
                                        .doubleValue(),
                                resp.getPrices()
                                        .getFirst()
                                        .getAsks()
                                        .getFirst()
                                        .getPrice()
                                        .doubleValue()
                        )
                ).toList().getFirst()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<HistoricalPriceResponse>> getHistoricals(InstrumentRequest instrumentRequest, Long count) {
        try {
            var request = new InstrumentCandlesRequest(new InstrumentName(instrumentRequest.getName()));
            request.setGranularity(instrumentRequest.getGranularity());
            request.setCount(count);

            var resp = context.instrument.candles(request);
            return Optional.of(
                    resp.getCandles().stream().map(candle ->
                        new HistoricalPriceResponse(
                                candle.getTime(),
                                instrumentRequest.getName(),
                                candle.getMid().getC().doubleValue()
                        )
                    ).toList()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
