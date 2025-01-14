package com.my.project.salaireservice.batchconfig;

import com.my.project.salaireservice.model.Salaire;
import com.my.project.salaireservice.repository.SalaireRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalaireWriter implements ItemWriter<Salaire> {

    private final SalaireRepository salaireRepository;

    public SalaireWriter(SalaireRepository salaireRepository) {
        this.salaireRepository = salaireRepository;
    }

    @Override
    public void write(Chunk<? extends Salaire> chunk) {
        System.out.println("Writing " + chunk.getItems().size() + " records.");
        for (Salaire salaire : chunk.getItems()) {
            System.out.println("Writing salaire with ID: " + salaire.getId());
            // Salary calculation logic
            double payPerHour = salaire.getBase() / 176;
            double finalSalaire = salaire.getBase()
                    + (salaire.getHeuresSupplementaires() * payPerHour)
                    + salaire.getPrimes()
                    - (salaire.getAbsences() * payPerHour);

            salaire.setMontant(finalSalaire);
        }
        salaireRepository.saveAll(chunk.getItems());
    }
}


