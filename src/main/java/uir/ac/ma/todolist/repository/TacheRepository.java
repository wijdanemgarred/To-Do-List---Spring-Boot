package uir.ac.ma.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uir.ac.ma.todolist.entity.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {
    // Additional query methods can be defined here
}
