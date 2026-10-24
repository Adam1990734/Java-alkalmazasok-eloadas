package com.example.javaalkalmazasokeloadas.controller.crud;

import com.example.javaalkalmazasokeloadas.model.crud.PagnetInfo;
import com.example.javaalkalmazasokeloadas.model.crud.invention.InventionRequestDto;
import com.example.javaalkalmazasokeloadas.model.crud.invention.InventionResponseDto;
import com.example.javaalkalmazasokeloadas.service.InventionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventions")
public class InventionRestController {
    private final InventionService inventionService;
    public InventionRestController(InventionService inventionService) {
        this.inventionService = inventionService;
    }
    @GetMapping
    public ResponseEntity<PagnetInfo<List<InventionResponseDto>>> findAll(
            @RequestParam(name = "len", defaultValue = "25") Integer len,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        var inventions = inventionService.findAll(page, len);
        return ResponseEntity.ok(inventions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InventionResponseDto> details(@PathVariable Long id) {
        InventionResponseDto invention = inventionService.findById(id);
        return ResponseEntity.ok(invention);
    }
    @PostMapping
    public ResponseEntity<InventionResponseDto> create(@RequestBody InventionRequestDto dto) {
        InventionResponseDto created = inventionService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<InventionResponseDto> update(@PathVariable Long id, @RequestBody InventionRequestDto dto) {
        InventionResponseDto updated = inventionService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
