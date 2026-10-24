package com.example.javaalkalmazasokeloadas.repository;

import com.example.javaalkalmazasokeloadas.model.crud.inventor.Inventor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventorRepository extends JpaRepository<Inventor, Long> {}
