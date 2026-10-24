package com.example.javaalkalmazasokeloadas.controller.crud;

import com.example.javaalkalmazasokeloadas.model.crud.inventor.InventorRequestDto;
import com.example.javaalkalmazasokeloadas.model.crud.inventor.InventorResponseDto;
import com.example.javaalkalmazasokeloadas.service.InventorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventors")
public class InventorRestController {
    private final InventorService inventorService;
    public InventorRestController(InventorService inventorService) {
        this.inventorService = inventorService;
    }
    @GetMapping
    public ResponseEntity<List<InventorResponseDto>> findAll() {
        List<InventorResponseDto> inventors = inventorService.findAll();
        return ResponseEntity.ok(inventors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InventorResponseDto> findById(@PathVariable Long id) {
        InventorResponseDto inventor = inventorService.findById(id);
        return ResponseEntity.ok(inventor);
    }
    @PostMapping
    public ResponseEntity<InventorResponseDto> create(@RequestBody InventorRequestDto dto) {
        InventorResponseDto created = inventorService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<InventorResponseDto> update(@PathVariable Long id, @RequestBody InventorRequestDto dto) {
        InventorResponseDto updated = inventorService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
