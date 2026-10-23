package com.example.javaalkalmazasokeloadas.service.imp;

import com.example.javaalkalmazasokeloadas.model.forex.Position.*;
import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import com.example.javaalkalmazasokeloadas.service.PositionService;
import com.oanda.Context;
import com.oanda.ExecuteException;
import com.oanda.RequestException;
import com.oanda.order.MarketOrderRequest;
import com.oanda.order.OrderCreateRequest;
import com.oanda.primitives.InstrumentName;
import com.oanda.trade.TradeCloseRequest;
import com.oanda.trade.TradeSpecifier;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImp implements PositionService {
    private final ApiKeysService apiKeysService;
    private final Context context;

    public PositionServiceImp(Context context, ApiKeysService apiKeysService) {
        this.context = context;
        this.apiKeysService = apiKeysService;
    }


    @Override
    public Optional<List<OpenedPositionResponse>> getAll(Long count) {
        try {
            var positions = context.trade.listOpen(apiKeysService.getAccountId()).getTrades();
            return Optional.of(
                    positions.stream().map(position ->
                        new OpenedPositionResponse(
                                position.getId(),
                                position.getOpenTime(),
                                position.getInstrument().toString(),
                                position.getInitialUnits().doubleValue(),
                                position.getCurrentUnits().doubleValue(),
                                position.getPrice().doubleValue(),
                                position.getUnrealizedPL().doubleValue()
                        )
                    ).limit(count).toList()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<OpenPositionResponse> open(OpenPositionRequest openPositionRequest) {
        try {
            var request = new OrderCreateRequest(apiKeysService.getAccountId());
            var marketOrderRequest = new MarketOrderRequest();

            marketOrderRequest.setInstrument(new InstrumentName(openPositionRequest.getName()));
            marketOrderRequest.setUnits(openPositionRequest.getUnit());
            request.setOrder(marketOrderRequest);

            var resp = context.order.create(request);
            return Optional.of(
                    new OpenPositionResponse(
                        resp.getLastTransactionID(),
                        openPositionRequest.getName(),
                        openPositionRequest.getUnit()
                )
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ClosePositionResponse> close(ClosePositionRequest closePositionRequest) {
        try {
            var request = new TradeCloseRequest(
                    apiKeysService.getAccountId(),
                    new TradeSpecifier(closePositionRequest.getId())
            );
            context.trade.close(request);
            return Optional.of(
                    new ClosePositionResponse(closePositionRequest.getId())
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
