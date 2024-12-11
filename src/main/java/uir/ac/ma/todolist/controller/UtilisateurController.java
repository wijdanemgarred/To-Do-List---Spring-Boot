package uir.ac.ma.todolist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uir.ac.ma.todolist.entity.Utilisateur;
import uir.ac.ma.todolist.service.UtilisateurService;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> register(@RequestBody @Valid Utilisateur utilisateur) {
        Utilisateur newUser = utilisateurService.register(utilisateur);
        return ResponseEntity.ok(newUser);
    }

    @Operation(
            summary = "Récupérer tous les utilisateurs",
            description = "Cette méthode récupère tous les utilisateurs du système"
    )
    // Endpoint to get all users
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        List<Utilisateur> users = utilisateurService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint to get a user by ID
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur trouvé"),
            @ApiResponse (responseCode = "404", description = "Utilisateur non trouvé")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUserById(@Parameter(description = "ID de l'utilisateur à récupérer", required =
            true)@PathVariable Long id) {
        Utilisateur user = utilisateurService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Endpoint to update a user
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUser = utilisateurService.updateUser(id, utilisateur);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint to delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<Utilisateur> login(@RequestBody Utilisateur utilisateur) {
        Utilisateur user = utilisateurService.login(utilisateur.getEmail(), utilisateur.getMot_de_passe());
        if (user != null) {
            return ResponseEntity.ok(user); // Return the logged-in user details (including ID)
        } else {
            return ResponseEntity.status(401).body(null);  // Unauthorized if login fails
        }

    }
}
