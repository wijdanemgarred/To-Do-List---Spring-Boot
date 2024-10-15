package uir.ac.ma.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uir.ac.ma.todolist.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    // Additional query methods can be defined here if needed
}
