package com.my.project.absenceservice.service;

import com.my.project.absenceservice.model.Presence;
import com.my.project.absenceservice.repository.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresenceService {

    @Autowired
    private PresenceRepository presenceRepository;

    public List<Presence> getAllPresences() {
        return presenceRepository.findAll();
    }

    public Presence getPresenceById(Long id) {
        return presenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presence not found"));
    }

    public Presence createPresence(Presence presence) {
        return presenceRepository.save(presence);
    }

    public Presence updatePresence(Long id, Presence updatedPresence) {
        Presence existingPresence = getPresenceById(id);
        existingPresence.setDate(updatedPresence.getDate());
        existingPresence.setCheckIn(updatedPresence.getCheckIn());
        existingPresence.setCheckOut(updatedPresence.getCheckOut());
        existingPresence.setStatus(updatedPresence.getStatus());
        existingPresence.setHeuresDAbsence(updatedPresence.getHeuresDAbsence());
        return presenceRepository.save(existingPresence);
    }

    public void deletePresence(Long id) {
        presenceRepository.deleteById(id);
    }
}

