package uir.ac.ma.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uir.ac.ma.todolist.entity.Utilisateur;
import uir.ac.ma.todolist.repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur register(Utilisateur utilisateur) {
        // Logic for user registration (e.g., hashing password)
        return utilisateurRepository.save(utilisateur);
    }

    // Get all users
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    // Get user by ID
    public Utilisateur getUserById(Long id) {
        Optional<Utilisateur> optionalUser = utilisateurRepository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Update user
    public Utilisateur updateUser(Long id, Utilisateur utilisateur) {
        // Check if the user exists
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }

        // Update the user's details
        utilisateur.setId(id); // Set the ID to the existing user
        return utilisateurRepository.save(utilisateur); // Save the updated user
    }

    // Delete user
    public void deleteUser(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        utilisateurRepository.deleteById(id); // Call the repository method to delete the user
    }

    // Simple login method
    public Utilisateur login(String email, String mot_de_passe) {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        if (user != null && user.getMot_de_passe().equals(mot_de_passe)) {
            return user;  // Return the user if login is successful
        }
        return null;  // Return null if authentication fails
    }
}