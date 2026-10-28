package com.example.javaalkalmazasokeloadas.service;

import com.example.javaalkalmazasokeloadas.model.crud.PagnetInfo;
import com.example.javaalkalmazasokeloadas.model.crud.inventor.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventorService {
    List<InventorResponseDto> findAll();
    PagnetInfo<List<InventorResponseDto>> findAll(int page, int size);
    List<InventorShortDto> findAllGetAsMinimal();
    List<InventorShortDto> findAllGetAsMinimal(int page, int size);
    InventorResponseDto findById(Long id);
    InventorResponseDto create(InventorRequestDto dto);
    InventorResponseDto update(Long id, InventorRequestDto dto);
    void delete(Long id);

    Long countAll();
    InventorRequestDto toSimpDto(InventorResponseDto inventor);

    List<InventorShortDto> findSuggestionsByNameAsMinimal(String name, Integer size);
}
