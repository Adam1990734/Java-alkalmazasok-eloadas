package com.example.javaalkalmazasokeloadas.service;

import com.example.javaalkalmazasokeloadas.model.forex.Position.*;
import com.oanda.ExecuteException;
import com.oanda.RequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PositionService {
    Optional<List<OpenedPositionResponse>> getAll(Long count);
    Optional<OpenPositionResponse> open(OpenPositionRequest openPositionRequest);
    Optional<ClosePositionResponse> close(ClosePositionRequest closePositionRequest);
}
