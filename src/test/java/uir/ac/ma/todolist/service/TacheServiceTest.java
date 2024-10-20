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

    @Test
    void getTacheById_ShouldReturnTache() {
        // Create a mock Tache object
        Tache tache = new Tache();
        tache.setId(1L);
        tache.setTitre("Faire le projet Angular");

        // Mock the repository to return the tache directly (no Optional)
        when(tacheRepository.findById(1L)).thenReturn(Optional.of(tache));

        // Call the service method
        Tache foundTache = tacheService.getTacheById(1L);

        // Verify that the correct task was returned
        assertEquals("Faire le projet Angular", foundTache.getTitre());

        // Verify that the repository method was called once
        verify(tacheRepository, times(1)).findById(1L);
    }


    @Test
    void deleteTache_ShouldCallDeleteMethod() {
        doNothing().when(tacheRepository).deleteById(1L);

        tacheService.deleteTache(1L);

        verify(tacheRepository, times(1)).deleteById(1L);
    }


    @Test
    void getTacheById_ShouldThrowExceptionWhenNotFound() {
        // Mock the repository to return an empty result
        when(tacheRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert that a RuntimeException is thrown when the task is not found
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tacheService.getTacheById(1L);
        });

        // Check that the exception message is correct
        assertEquals("Tache not found with id: 1", exception.getMessage());

        // Verify that the repository method was called once
        verify(tacheRepository, times(1)).findById(1L);
    }
}
