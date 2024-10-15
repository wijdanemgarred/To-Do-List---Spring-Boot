package uir.ac.ma.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uir.ac.ma.todolist.entity.Tache;
import uir.ac.ma.todolist.repository.TacheRepository;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    // Additional methods
}
