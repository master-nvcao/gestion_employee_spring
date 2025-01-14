package com.my.project.salaireservice.batchconfig;

import com.my.project.salaireservice.model.Salaire;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SalaireProcessor implements ItemProcessor<Salaire, Salaire> {

    @Override
    public Salaire process(Salaire salaire) throws Exception {
        double payPerHour = salaire.getBase() / 176; // Hourly rate

        // Calculate final salary
        double salaireFinal = salaire.getBase()
                + (salaire.getHeuresSupplementaires() * payPerHour)
                + salaire.getPrimes()
                - (salaire.getAbsences() * payPerHour);

        salaire.setMontant(salaireFinal); // Update montant with the calculated salary
        return salaire;
    }
}
