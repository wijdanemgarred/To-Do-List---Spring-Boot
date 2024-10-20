package uir.ac.ma.todolist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uir.ac.ma.todolist.entity.Tache;
import uir.ac.ma.todolist.repository.TacheRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TacheServiceTest {

    @Mock
    private TacheRepository tacheRepository;

    @InjectMocks
    private TacheService tacheService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTache_ShouldReturnNewTache() {
        Tache tache = new Tache();
        tache.setTitre("Faire le projet Angular");

        when(tacheRepository.save(any(Tache.class))).thenReturn(tache);

        Tache createdTache = tacheService.createTache(tache);

        assertNotNull(createdTache);
        assertEquals("Faire le projet Angular", createdTache.getTitre());
        verify(tacheRepository, times(1)).save(tache);
    }


}
