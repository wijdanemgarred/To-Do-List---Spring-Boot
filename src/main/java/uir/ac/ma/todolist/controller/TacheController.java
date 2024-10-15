package uir.ac.ma.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uir.ac.ma.todolist.entity.Tache;
import uir.ac.ma.todolist.service.TacheService;

@RestController
@RequestMapping("/api/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @PostMapping
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        Tache newTache = tacheService.createTache(tache);
        return ResponseEntity.ok(newTache);
    }

    // Additional endpoints
}
