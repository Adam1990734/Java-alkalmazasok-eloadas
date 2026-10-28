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
    private Integer bornat;

    @Column(name = "meghal", nullable = true)
    private Integer diedat;

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

    public Integer getBornat() {
        return bornat;
    }

    public void setBornat(Integer bornat) {
        this.bornat = bornat;
    }

    public Integer getDiedat() {
        return diedat;
    }

    public void setDiedat(Integer diedat) { this.diedat = diedat; }

    public Set<Invention> getInventions() {
        return inventions;
    }

    public void setInventions(Set<Invention> inventions) { this.inventions = inventions; }
}
