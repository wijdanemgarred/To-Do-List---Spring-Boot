package uir.ac.ma.todolist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uir.ac.ma.todolist.entity.Utilisateur;
import uir.ac.ma.todolist.repository.UtilisateurRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUtilisateur_ShouldReturnNewUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("John Doe");

        when(utilisateurRepository.save(any(Utilisateur.class))).thenReturn(utilisateur);

        Utilisateur createdUtilisateur = utilisateurService.register(utilisateur);

        assertNotNull(createdUtilisateur);
        assertEquals("John Doe", createdUtilisateur.getNom());
        verify(utilisateurRepository, times(1)).save(utilisateur);
    }



    @Test
    void getUtilisateurById_ShouldReturnUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("John Doe");

        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(utilisateur));  // Mocking a found user

        Utilisateur foundUtilisateur = utilisateurService.getUserById(1L);  // No Optional

        assertNotNull(foundUtilisateur);  // Assert user is not null
        assertEquals("John Doe", foundUtilisateur.getNom());  // Check the name
        verify(utilisateurRepository, times(1)).findById(1L);  // Verify the interaction with repository
    }

    @Test
    void getUtilisateurById_ShouldThrowExceptionWhenNotFound() {
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.empty());  // Mocking user not found

        Exception exception = assertThrows(RuntimeException.class, () -> {
            utilisateurService.getUserById(1L);  // Should throw an exception
        });

        assertEquals("User not found with id: 1", exception.getMessage());  // Check the exception message
        verify(utilisateurRepository, times(1)).findById(1L);  // Verify repository interaction
    }

    @Test
    void deleteUtilisateur_ShouldCallDeleteMethod() {
        // Mock that the user exists
        when(utilisateurRepository.existsById(1L)).thenReturn(true);
        doNothing().when(utilisateurRepository).deleteById(1L);  // Mock the delete method

        // Call the service method
        utilisateurService.deleteUser(1L);

        // Verify that the delete method was called once
        verify(utilisateurRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUtilisateur_ShouldThrowExceptionWhenUserNotFound() {
        // Mock that the user does not exist
        when(utilisateurRepository.existsById(1L)).thenReturn(false);

        // Call the delete method and expect an exception
        Exception exception = assertThrows(RuntimeException.class, () -> {
            utilisateurService.deleteUser(1L);
        });

        // Check that the exception message matches
        assertEquals("User not found with id: 1", exception.getMessage());

        // Verify that deleteById was never called because the user doesn't exist
        verify(utilisateurRepository, never()).deleteById(1L);
    }


}
