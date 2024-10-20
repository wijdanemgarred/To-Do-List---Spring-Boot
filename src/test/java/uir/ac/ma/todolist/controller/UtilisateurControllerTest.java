package uir.ac.ma.todolist.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import uir.ac.ma.todolist.entity.Utilisateur;
import uir.ac.ma.todolist.service.UtilisateurService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UtilisateurControllerTest {

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private UtilisateurController utilisateurController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUtilisateur_ShouldReturnCreatedUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("John Doe");

        when(utilisateurService.register(any(Utilisateur.class))).thenReturn(utilisateur);

        ResponseEntity<Utilisateur> response = utilisateurController.register(utilisateur);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getNom());
        verify(utilisateurService, times(1)).register(utilisateur);
    }

    @Test

    void getUtilisateurById_ShouldReturnUtilisateur() {
        // Create a mock Utilisateur object
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("John Doe");

        // Mock the service to return the utilisateur object directly (no Optional)
        when(utilisateurService.getUserById(1L)).thenReturn(utilisateur);

        // Call the controller method
        ResponseEntity<Utilisateur> response = utilisateurController.getUserById(1L);

        // Verify the status and returned data
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getNom());

        // Verify that the service method was called once
        verify(utilisateurService, times(1)).getUserById(1L);
    }



}
