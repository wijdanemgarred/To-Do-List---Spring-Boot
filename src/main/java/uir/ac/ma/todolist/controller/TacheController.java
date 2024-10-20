package uir.ac.ma.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uir.ac.ma.todolist.entity.Tache;
import uir.ac.ma.todolist.service.TacheService;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @PostMapping("/add")
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        Tache newTache = tacheService.createTache(tache);
        return ResponseEntity.ok(newTache);
    }

    // Additional endpoints
    @GetMapping("/get")
    public ResponseEntity<List<Tache>> getAllTaches() {
        List<Tache> taches = tacheService.getAllTaches();
        return ResponseEntity.ok(taches);
    }

    // Récupérer une tâche par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTacheById(@PathVariable Long id) {
        Tache tache = tacheService.getTacheById(id);
        return ResponseEntity.ok(tache);
    }

    // Mettre à jour une tâche
    @PutMapping("/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tacheDetails) {
        Tache updatedTache = tacheService.updateTache(id, tacheDetails);
        return ResponseEntity.ok(updatedTache);
    }

    // Supprimer une tâche
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.noContent().build();
    }
}
