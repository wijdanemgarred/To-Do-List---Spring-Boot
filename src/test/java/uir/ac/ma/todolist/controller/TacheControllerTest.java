package uir.ac.ma.todolist.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import uir.ac.ma.todolist.entity.Tache;
import uir.ac.ma.todolist.service.TacheService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TacheControllerTest {

    @Mock
    private TacheService tacheService;

    @InjectMocks
    private TacheController tacheController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTache_ShouldReturnCreatedTache() {
        Tache tache = new Tache();
        tache.setTitre("Faire le projet Angular");

        when(tacheService.createTache(any(Tache.class))).thenReturn(tache);

        ResponseEntity<Tache> response = tacheController.createTache(tache);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Faire le projet Angular", response.getBody().getTitre());
        verify(tacheService, times(1)).createTache(tache);
    }

    @Test
    void getTacheById_ShouldReturnTache() {
        Tache tache = new Tache();
        tache.setId(1L);
        tache.setTitre("Faire le projet Angular");

        when(tacheService.getTacheById(1L)).thenReturn(tache);

        ResponseEntity<Tache> response = tacheController.getTacheById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Faire le projet Angular", response.getBody().getTitre());
        verify(tacheService, times(1)).getTacheById(1L);
    }

    @Test
    void getTacheById_ShouldReturnNotFound() {
        when(tacheService.getTacheById(1L)).thenThrow(new RuntimeException("Tache not found with id: 1"));

        ResponseEntity<Tache> response = tacheController.getTacheById(1L);

        assertEquals(404, response.getStatusCodeValue());
        verify(tacheService, times(1)).getTacheById(1L);
    }


}
