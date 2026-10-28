package com.example.javaalkalmazasokeloadas.controller.crud;

import com.example.javaalkalmazasokeloadas.model.crud.inventor.InventorRequestDto;
import com.example.javaalkalmazasokeloadas.model.crud.inventor.InventorResponseDto;
import com.example.javaalkalmazasokeloadas.model.crud.inventor.InventorShortDto;
import com.example.javaalkalmazasokeloadas.service.InventorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.javaalkalmazasokeloadas.model.crud.PagnetInfo;

import java.util.List;

@RestController
@RequestMapping("/api/inventors")
public class InventorRestController {
    private final InventorService inventorService;
    public InventorRestController(InventorService inventorService) {
        this.inventorService = inventorService;
    }
    @GetMapping
    public ResponseEntity<PagnetInfo<List<InventorResponseDto>>> findAll(
            @RequestParam(name = "len", defaultValue = "25") Integer len,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        var inventors = inventorService.findAll(page, len);
        return ResponseEntity.ok(inventors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InventorResponseDto> details(@PathVariable Long id) {
        InventorResponseDto inventor = inventorService.findById(id);
        return ResponseEntity.ok(inventor);
    }
    @PostMapping("/create")
    public ResponseEntity<InventorResponseDto> create(@RequestBody InventorRequestDto dto) {
        InventorResponseDto created = inventorService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<InventorResponseDto> update(@PathVariable Long id, @RequestBody InventorRequestDto dto) {
        InventorResponseDto updated = inventorService.update(id, dto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventorService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/suggest")
    public ResponseEntity<List<InventorShortDto>> suggestByName(
            @RequestParam(name = "name", defaultValue = "") String name
    ) {
        if(name.isEmpty())
            return ResponseEntity.ok(
                    inventorService.findAllGetAsMinimal(0, 10)
            );
        var inventors = inventorService.findSuggestionsByNameAsMinimal(name, 10);
        return ResponseEntity.ok(inventors);
    }
}
