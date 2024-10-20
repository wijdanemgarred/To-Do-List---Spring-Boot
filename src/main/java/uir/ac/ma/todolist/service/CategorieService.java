package uir.ac.ma.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uir.ac.ma.todolist.entity.Categorie;
import uir.ac.ma.todolist.repository.CategorieRepository;



import java.util.List;
import java.util.Optional;

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
    public Optional<Categorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}

