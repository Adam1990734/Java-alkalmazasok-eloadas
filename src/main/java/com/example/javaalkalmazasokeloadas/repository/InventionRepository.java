package com.example.javaalkalmazasokeloadas.repository;

import com.example.javaalkalmazasokeloadas.model.crud.invention.Invention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventionRepository extends JpaRepository<Invention, Long> { }
