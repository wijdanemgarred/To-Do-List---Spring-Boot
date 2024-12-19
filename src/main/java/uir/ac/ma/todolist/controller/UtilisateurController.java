package uir.ac.ma.todolist.controller;

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
    public ResponseEntity<Utilisateur> register(@RequestBody Utilisateur utilisateur) {
        Utilisateur newUser = utilisateurService.register(utilisateur);
        return ResponseEntity.ok(newUser);
    }


    // Endpoint to get all users
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUsers() {
        List<Utilisateur> users = utilisateurService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint to get a user by ID

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Long id) {
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
    public ResponseEntity<Utilisateur> login(@RequestBody LoginRequest loginRequest) {
        try {
            Utilisateur user = utilisateurService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(user); // Return the logged-in user details (including ID)
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null);  // Unauthorized if login fails
        }
    }

    //a simple DTO (Data Transfer Object)
    // LoginRequest should be static
    public static class LoginRequest {
        private String email;
        private String password;

        // Default constructor is required for deserialization
        public LoginRequest() {
        }

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
