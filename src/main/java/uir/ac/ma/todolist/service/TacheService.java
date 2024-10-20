package uir.ac.ma.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uir.ac.ma.todolist.entity.Tache;
import uir.ac.ma.todolist.repository.TacheRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }



    // Récupérer toutes les tâches
    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    // Récupérer une tâche par son ID
    public Tache getTacheById(Long id) {
        Optional<Tache> optionalTache = tacheRepository.findById(id);
        return optionalTache.orElseThrow(() -> new RuntimeException("Tâche non trouvée avec l'ID : " + id));
    }

    // Mettre à jour une tâche existante
    public Tache updateTache(Long id, Tache tacheDetails) {
        Tache tache = getTacheById(id); // Récupère la tâche existante par son ID
        tache.setTitre(tacheDetails.getTitre());
        tache.setDescription(tacheDetails.getDescription());
        tache.setDeadline(tacheDetails.getDeadline());
        tache.setCategorie(tacheDetails.getCategorie());
        // Mettre à jour d'autres champs si nécessaire

        return tacheRepository.save(tache); // Sauvegarder la tâche mise à jour
    }

    // Supprimer une tâche par son ID
    public void deleteTache(Long id) {
        Tache tache = getTacheById(id); // Récupère la tâche existante par son ID
        tacheRepository.delete(tache); // Supprime la tâche
    }
}
