package uir.ac.ma.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uir.ac.ma.todolist.entity.Categorie;
import uir.ac.ma.todolist.repository.CategorieRepository;



import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    // Add this method to retrieve all categories
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    // Additional methods (if needed)
}

