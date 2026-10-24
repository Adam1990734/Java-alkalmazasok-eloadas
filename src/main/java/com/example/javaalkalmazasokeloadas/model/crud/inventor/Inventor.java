package com.example.javaalkalmazasokeloadas.model.crud.inventor;

import com.example.javaalkalmazasokeloadas.model.crud.invention.Invention;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "kutato")
public class Inventor {
    @Id
    @Column(name = "fkod")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nev", length = 40, nullable = false)
    private String name;

    @Column(name = "szul", nullable = false)
    private int bornat;

    @Column(name = "meghal", nullable = true)
    private int diedat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "kapcsol",
            joinColumns = @JoinColumn(name = "tkod"),
            inverseJoinColumns = @JoinColumn(name = "fkod")
    )
    private Set<Invention> inventions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBornat() {
        return bornat;
    }

    public void setBornat(int bornat) {
        this.bornat = bornat;
    }

    public int getDiedat() {
        return diedat;
    }

    public void setDiedat(int diedat) {
        this.diedat = diedat;
    }

    public Set<Invention> getInventions() {
        return inventions;
    }

    public void addInvention(Invention invention) {
        this.inventions.add(invention);
    }
}
