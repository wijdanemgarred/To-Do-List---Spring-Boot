package uir.ac.ma.todolist.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uir.ac.ma.todolist.entity.Categorie;
import uir.ac.ma.todolist.repository.CategorieRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategorieServiceTest {

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieService categorieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategorie_ShouldReturnNewCategorie() {
        Categorie categorie = new Categorie();
        categorie.setNom("School");

        when(categorieRepository.save(any(Categorie.class))).thenReturn(categorie);

        Categorie createdCategorie = categorieService.createCategorie(categorie);

        assertNotNull(createdCategorie);
        assertEquals("School", createdCategorie.getNom());
        verify(categorieRepository, times(1)).save(categorie);
    }


    @Test
    void getCategorieById_ShouldReturnCategorie() {
        Categorie categorie = new Categorie();
        categorie.setId(1L);
        categorie.setNom("School");

        when(categorieRepository.findById(1L)).thenReturn(Optional.of(categorie));

        Optional<Categorie> foundCategorie = categorieService.getCategorieById(1L);

        assertTrue(foundCategorie.isPresent());
        assertEquals("School", foundCategorie.get().getNom());
        verify(categorieRepository, times(1)).findById(1L);
    }

    @Test
    void getCategorieById_ShouldReturnEmpty() {
        when(categorieRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Categorie> foundCategorie = categorieService.getCategorieById(1L);

        assertFalse(foundCategorie.isPresent());
        verify(categorieRepository, times(1)).findById(1L);
    }

}
