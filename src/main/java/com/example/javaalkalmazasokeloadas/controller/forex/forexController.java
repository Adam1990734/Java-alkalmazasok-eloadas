package com.example.javaalkalmazasokeloadas.controller;

import com.example.javaalkalmazasokeloadas.model.forex.AccountInfo;
import com.example.javaalkalmazasokeloadas.model.forex.HistoricalPriceResponse;
import com.example.javaalkalmazasokeloadas.model.forex.Instrument.InstrumentRequest;
import com.example.javaalkalmazasokeloadas.model.forex.Instrument.InstrumentResponse;
import com.example.javaalkalmazasokeloadas.model.forex.Position.*;
import com.example.javaalkalmazasokeloadas.service.ApiKeysService;
import com.oanda.ExecuteException;
import com.oanda.RequestException;
import com.oanda.account.AccountInstrumentsRequest;
import com.oanda.instrument.InstrumentCandlesRequest;
import com.oanda.order.MarketOrderRequest;
import com.oanda.order.OrderCreateRequest;
import com.oanda.pricing.PricingGetRequest;
import com.oanda.primitives.InstrumentName;
import com.oanda.trade.TradeCloseRequest;
import com.oanda.trade.TradeSpecifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.oanda.Context;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/forex")
public class forexController {
    private final Context context;
    private final ApiKeysService apiKeysService;

    public forexController(ApiKeysService apiKeysService, Context context) {
        this.apiKeysService = apiKeysService;
        this.context = context;
    }

    @GetMapping
    public String index(Model model) throws ExecuteException, RequestException {
        var info = context.account.summary(
                apiKeysService.getAccountId()
        ).getAccount();
        model.addAttribute("accountInfo", new AccountInfo(
                info.getBalance(),
                info.getWithdrawalLimit(),
                info.getOpenPositionCount(),
                info.getOpenTradeCount(),
                info.getPendingOrderCount()
        ));
        return "forex/index";
    }

    @GetMapping("/instruments")
    public String selectInstruments(
            @RequestParam(name = "len", defaultValue = "50") Integer len,
            Model model
    ) throws ExecuteException, RequestException {
        var request = new AccountInstrumentsRequest(apiKeysService.getAccountId());
        var resp = context.account.instruments(request);

        var instruments = new ArrayList<String>();
        for(var instrument : resp.getInstruments()) {
            instruments.add(instrument.getDisplayName());
            if(instruments.size() == len)
                break;
        }
        model.addAttribute("availableInstruments", instruments);
        model.addAttribute("availableGranularities", List.of(
                "M1",
                "H1",
                "D",
                "W",
                "M"
        ));
        //Sima ár és hozzá a hisztorikus árak lekérdezéséhez:
        model.addAttribute("chosenInstrument", new InstrumentRequest());
        //Nyitáshoz:
        model.addAttribute("chosenInstrumentToOpen", new OpenPositionRequest());
        //Zárás:
        model.addAttribute("chosenTradeIdToClose", new ClosePositionRequest());
        return "forex/instruments";
    }

    @PostMapping("/actprice")
    public String actualPrice(
            @ModelAttribute InstrumentRequest instrumentRequest,
            Model model
    ) throws ExecuteException, RequestException {
        var request = new PricingGetRequest(
                apiKeysService.getAccountId(),
                List.of(instrumentRequest.getName())
        );
        var resp = context.pricing.get(request);
        model.addAttribute("price",
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
        );
        return "forex/actprice";
    }

    @PostMapping("/histprice")
    public String historical(
            @ModelAttribute InstrumentRequest instrumentRequest,
            Model model
    ) throws ExecuteException, RequestException {
        var request = new InstrumentCandlesRequest(new InstrumentName(instrumentRequest.getName()));
        request.setGranularity(instrumentRequest.getGranularity());
        request.setCount(10L);
        var resp = context.instrument.candles(request);

        model.addAttribute("prices", resp.getCandles().stream().map(candle ->
                new HistoricalPriceResponse(
                        candle.getTime(),
                        instrumentRequest.getName(),
                        candle.getMid().getC().doubleValue()
                )
            ).toList()
        );
        return "forex/histprice";
    }

    @PostMapping("/openpos")
    public String openPosition(
            @ModelAttribute OpenPositionRequest openPositionRequest,
            Model model
    ) throws ExecuteException, RequestException {
        var request = new OrderCreateRequest(apiKeysService.getAccountId());
        var marketOrderRequest = new MarketOrderRequest();

        marketOrderRequest.setInstrument(new InstrumentName(openPositionRequest.getName()));
        marketOrderRequest.setUnits(openPositionRequest.getUnit());
        request.setOrder(marketOrderRequest);

        var resp = context.order.create(request);
        model.addAttribute("position", new OpenPositionResponse(
           resp.getLastTransactionID(),
           openPositionRequest.getName(),
           openPositionRequest.getUnit()
        ));
        return "forex/openpos";
    }

    @PostMapping("/closepos")
    public String closePosition(
            @ModelAttribute ClosePositionRequest closePositionRequest,
            Model model
    ) throws ExecuteException, RequestException {
        var request = new TradeCloseRequest(
                apiKeysService.getAccountId(),
                new TradeSpecifier(closePositionRequest.getId())
        );
        context.trade.close(request);
        model.addAttribute("position", new ClosePositionResponse(
                closePositionRequest.getId()
        ));
        return "forex/closepos";
    }

    @GetMapping("/openedpos")
    public String openedPositions(Model model) throws ExecuteException, RequestException {
        var positions = context.trade.listOpen(apiKeysService.getAccountId()).getTrades();
        model.addAttribute("positions", positions.stream().map(position ->
                new OpenedPositionResponse(
                        position.getId(),
                        position.getOpenTime(),
                        position.getInstrument().toString(),
                        position.getInitialUnits().doubleValue(),
                        position.getCurrentUnits().doubleValue(),
                        position.getPrice().doubleValue(),
                        position.getUnrealizedPL().doubleValue()
                )
            ).toList()
        );
        return "forex/openedpos";
    }
}
