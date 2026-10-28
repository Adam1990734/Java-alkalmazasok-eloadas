package com.example.javaalkalmazasokeloadas.service.imp;

import com.example.javaalkalmazasokeloadas.model.crud.PagnetInfo;
import com.example.javaalkalmazasokeloadas.model.crud.invention.InventionShortDto;
import com.example.javaalkalmazasokeloadas.model.crud.inventor.*;
import com.example.javaalkalmazasokeloadas.repository.InventionRepository;
import com.example.javaalkalmazasokeloadas.repository.InventorRepository;
import com.example.javaalkalmazasokeloadas.service.InventorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventorServiceImp implements InventorService {
    private final InventorRepository repository;
    private  final InventionRepository inventionRepository;

    public InventorServiceImp(InventorRepository repository, InventionRepository inventionRepository) {
        this.repository = repository;
        this.inventionRepository = inventionRepository;
    }

    @Override
    public List<InventorResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public InventorResponseDto findById(Long id) {
        Inventor inventor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventor not found"));
        return toDto(inventor);
    }

    @Override
    public InventorResponseDto create(InventorRequestDto dto) {
        Inventor inventor = new Inventor();
        inventor.setName(dto.getName());
        inventor.setBornat(dto.getBornat());
        inventor.setDiedat(dto.getDiedat());
        inventor.setInventions(new HashSet<>(
                this.inventionRepository.findAllById(dto.getInventions())
        ));
        return toDto(repository.save(inventor));
    }

    @Override
    public InventorResponseDto update(
            Long id,
            InventorRequestDto dto) {
        Inventor inventor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventor not found"));
        inventor.setName(dto.getName());
        inventor.setBornat(dto.getBornat());
        inventor.setDiedat(dto.getDiedat());
        inventor.setInventions(new HashSet<>(
                this.inventionRepository.findAllById(dto.getInventions())
        ));
        return toDto(repository.save(inventor));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PagnetInfo<List<InventorResponseDto>> findAll(int page, int size) {
        var data = repository.findAll(
                        PageRequest.of(
                                page,
                                size
                        )
                )
                .stream()
                .map(this::toDto)
                .toList();
        return new PagnetInfo<List<InventorResponseDto>>(
                page,
                size,
                (int)(countAll() / size),
                data
        );
    }
    @Override
    public List<InventorShortDto> findAllGetAsMinimal() {
        return repository.findAll()
                .stream()
                .map(this::toShortDto)
                .toList();
    }
    @Override
    public List<InventorShortDto> findAllGetAsMinimal(int page, int size) {
        return repository.findAll(
                        PageRequest.of(
                                page,
                                size
                        )
                )
                .stream()
                .map(this::toShortDto)
                .toList();
    }

    @Override
    public Long countAll() { return repository.count(); }

    @Override
    public List<InventorShortDto> findSuggestionsByNameAsMinimal(String name, Integer size) {
        var data = repository.findAllByNameContainingIgnoreCase(
                name,
                PageRequest.of(0, size)
        );
        return data.stream()
                .map(this::toShortDto)
                .toList();
    }

    private InventorResponseDto toDto(Inventor inventor) {
        InventorResponseDto dto = new InventorResponseDto();
        dto.setId(inventor.getId());
        dto.setName(inventor.getName());
        dto.setBornat(inventor.getBornat());
        dto.setDiedat(inventor.getDiedat());
        dto.setInventions(
                inventor.getInventions()
                        .stream()
                        .map(e -> new InventionShortDto(e.getId(), e.getName()))
                        .collect(Collectors.toSet())
        );
        return dto;
    }
    private InventorShortDto toShortDto(Inventor inventor) {
        InventorShortDto dto = new InventorShortDto();
        dto.setId(inventor.getId());
        dto.setName(inventor.getName());
        return dto;
    }
    @Override
    public InventorRequestDto toSimpDto(InventorResponseDto inventor) {
        var dto = new InventorRequestDto();
        dto.setName(inventor.getName());
        dto.setBornat(inventor.getBornat());
        dto.setDiedat(inventor.getDiedat());
        dto.setInventions(inventor.getInventions().stream().mapToLong(InventionShortDto::getId).boxed().collect(Collectors.toSet()));
        return dto;
    }
}
