package uir.ac.ma.todolist.entity;

import jakarta.persistence.*;


import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide.")
    @Size(min = 2, max = 100, message = "Le nom doit avoir entre 2 et 100 caractères.")
    private String nom;

    @NotNull(message = "L'email ne peut pas être nul.")
    @Email(message = "L'email doit être valide.")
    @Column(unique = true)
    private String email;

    private String mot_de_passe;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Tache> taches;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Set<Tache> getTaches() {
        return taches;
    }

    public void setTaches(Set<Tache> taches) {
        this.taches = taches;
    }
}
