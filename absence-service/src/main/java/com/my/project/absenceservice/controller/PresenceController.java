package com.my.project.absenceservice.controller;

import com.my.project.absenceservice.model.Presence;
import com.my.project.absenceservice.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presences")
public class PresenceController {

    @Autowired
    private PresenceService presenceService;

    @GetMapping
    public List<Presence> getAllPresences() {
        return presenceService.getAllPresences();
    }

    @GetMapping("/{id}")
    public Presence getPresenceById(@PathVariable Long id) {
        return presenceService.getPresenceById(id);
    }

    @PostMapping
    public Presence createPresence(@RequestBody Presence presence) {
        return presenceService.createPresence(presence);
    }

    @PutMapping("/{id}")
    public Presence updatePresence(@PathVariable Long id, @RequestBody Presence presence) {
        return presenceService.updatePresence(id, presence);
    }

    @DeleteMapping("/{id}")
    public void deletePresence(@PathVariable Long id) {
        presenceService.deletePresence(id);
    }
}

