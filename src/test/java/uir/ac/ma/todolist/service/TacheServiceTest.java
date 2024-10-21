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
    void createTache_ShouldReturnNewTache_WithID() {
        Tache tache = new Tache();
        tache.setTitre("Faire le projet Angular");

        // Assume the repository's findById method returns Optional.empty() for ID 1
        when(tacheRepository.findById(1L)).thenReturn(Optional.empty());

        // Mock the save method to return the tache with ID set
        when(tacheRepository.save(any(Tache.class))).thenAnswer(invocation -> {
            Tache t = invocation.getArgument(0);
            t.setId(1L); // Set the ID to 1
            return t;
        });

        Tache createdTache = tacheService.createTache(tache);

        assertNotNull(createdTache);
        assertEquals("Faire le projet Angular", createdTache.getTitre());
        assertEquals(1, createdTache.getId()); // Check that the ID is set to 1
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
        // Mock the behavior of existsById to return true
        when(tacheRepository.existsById(1L)).thenReturn(true);

        // Mock the behavior of deleteById
        doNothing().when(tacheRepository).deleteById(1L);

        // Call the service method
        tacheService.deleteTache(1L);

        // Verify that both existsById and deleteById were called once
        verify(tacheRepository, times(1)).existsById(1L);
        verify(tacheRepository, times(1)).deleteById(1L);
    }


    @Test
    void getTacheById_ShouldThrowExceptionWhenNotFound() {
        // Mock the repository to return an empty result
        when(tacheRepository.findById(2L)).thenReturn(Optional.empty());

        // Assert that a RuntimeException is thrown when the task is not found
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tacheService.getTacheById(1L);
        });

        // Check that the exception message is correct
        assertEquals("Tâche non trouvée avec l'ID : 1", exception.getMessage());

        // Verify that the repository method was called once
        verify(tacheRepository, times(1)).findById(1L);
    }
}
