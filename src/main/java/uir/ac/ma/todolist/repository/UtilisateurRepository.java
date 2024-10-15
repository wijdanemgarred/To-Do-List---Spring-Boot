package uir.ac.ma.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uir.ac.ma.todolist.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
}

