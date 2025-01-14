package com.my.project.salaireservice.batchconfig;

import com.my.project.salaireservice.model.Salaire;
import com.my.project.salaireservice.repository.SalaireRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class SalaireItemReader implements ItemReader<Salaire> {

    private final SalaireRepository salaireRepository;
    private Iterator<Salaire> iterator;

    public SalaireItemReader(SalaireRepository salaireRepository) {
        this.salaireRepository = salaireRepository;
    }

    @Override
    public Salaire read() throws Exception {
        // Only load data once during the job execution
        if (iterator == null) {
            List<Salaire> salaires = salaireRepository.findAll();
            System.out.println("Loaded " + salaires.size() + " records from the database.");
            iterator = salaires.iterator();
        }

        // Return the next item or null when no more data
        if (iterator != null && iterator.hasNext()) {
            Salaire salaire = iterator.next();
            System.out.println("Reading salaire: " + salaire.getId());
            return salaire;
        } else {
            System.out.println("No more data to read.");
            return null; // Signal end of data
        }
    }
}


