package com.example.javaalkalmazasokeloadas.service;

import com.example.javaalkalmazasokeloadas.model.crud.PagnetInfo;
import com.example.javaalkalmazasokeloadas.model.crud.invention.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventionService {
    List<InventionResponseDto> findAll();
    PagnetInfo<List<InventionResponseDto>> findAll(int page, int size);
    List<InventionShortDto> findAllGetAsMinimal();
    List<InventionShortDto> findAllGetAsMinimal(int page, int size);
    InventionResponseDto findById(Long id);
    InventionResponseDto create(InventionRequestDto dto);
    InventionResponseDto update(Long id, InventionRequestDto dto);
    void delete(Long id);

    Long countAll();

    InventionRequestDto toSimpDto(InventionResponseDto invention);

    List<InventionShortDto> findSuggestionsByNameAsMinimal(String name, Integer size);

    boolean Exist(Long id);
}
