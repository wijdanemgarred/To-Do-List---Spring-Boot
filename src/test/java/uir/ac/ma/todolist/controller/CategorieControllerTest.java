package uir.ac.ma.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import uir.ac.ma.todolist.entity.Categorie;
import uir.ac.ma.todolist.service.CategorieService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategorieControllerTest {

    @Mock
    private CategorieService categorieService;

    @InjectMocks
    private CategorieController categorieController;


    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategorie_ShouldReturnCreatedCategorie() throws Exception {
        Categorie categorie = new Categorie();
        categorie.setNom("School");

        when(categorieService.createCategorie(any(Categorie.class))).thenReturn(categorie);

        ResponseEntity<Categorie> response = categorieController.createCategorie(categorie);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("School", response.getBody().getNom());
        verify(categorieService, times(1)).createCategorie(categorie);
    }

}
