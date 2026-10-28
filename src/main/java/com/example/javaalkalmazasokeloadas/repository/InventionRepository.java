package com.example.javaalkalmazasokeloadas.repository;

import com.example.javaalkalmazasokeloadas.model.crud.invention.Invention;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventionRepository extends JpaRepository<Invention, Long> {
    List<Invention> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
