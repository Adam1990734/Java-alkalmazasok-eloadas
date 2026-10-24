package com.example.javaalkalmazasokeloadas.seed;

import com.example.javaalkalmazasokeloadas.model.crud.invention.Invention;
import com.example.javaalkalmazasokeloadas.model.crud.inventor.Inventor;
import com.example.javaalkalmazasokeloadas.repository.InventionRepository;
import com.example.javaalkalmazasokeloadas.repository.InventorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final InventorRepository inventorRepository;
    private final InventionRepository inventionRepository;

    public DatabaseSeeder(InventorRepository inventorRepository, InventionRepository inventionRepository) {
        this.inventorRepository = inventorRepository;
        this.inventionRepository = inventionRepository;
    }

    private final Map<Long, Inventor> inventors = new HashMap<>();
    private final Map<Long, Invention> inventions = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        if (inventorRepository.count() > 0 && inventionRepository.count() > 0)
            return;
        loadInventions();
        loadInventors();
        loadRelations();
        inventorRepository.saveAll(inventors.values());
    }

    private void loadInventions() throws IOException {

        ClassPathResource resource = new ClassPathResource("seed/talalmany.txt");

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     resource.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\t", 2);

                Long tkod = Long.parseLong(parts[0]);

                Invention invention = new Invention();
                invention.setName(parts[1]);

                inventionRepository.save(invention);

                inventions.put(tkod, invention);
            }
        }
    }

    private void loadInventors() throws IOException {

        ClassPathResource resource =
                new ClassPathResource("seed/kutato.txt");

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     resource.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\t");

                Long fkod = Long.parseLong(parts[0]);

                Inventor inventor = new Inventor();
                inventor.setName(parts[1]);
                inventor.setBornat(Integer.parseInt(parts[2]));

                if (parts.length > 3 && !parts[3].isBlank())
                    inventor.setDiedat(Integer.parseInt(parts[3]));

                inventorRepository.save(inventor);

                inventors.put(fkod, inventor);
            }
        }
    }

    private void loadRelations() throws IOException {
        ClassPathResource resource =
                new ClassPathResource("seed/kapcsol.txt");
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     resource.getInputStream(),
                                     StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\t");

                Long tkod = Long.parseLong(parts[0]);
                Long fkod = Long.parseLong(parts[1]);

                Invention invention = inventions.get(tkod);
                Inventor inventor = inventors.get(fkod);

                if (inventor != null && invention != null)
                    inventor.getInventions().add(invention);
            }
        }
    }
}
