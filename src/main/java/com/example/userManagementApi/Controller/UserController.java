package com.example.userManagementApi.Controller;


import com.example.userManagementApi.model.User;
import com.example.userManagementApi.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController // Marks this class as a REST controller, handling incoming HTTP requests
@RequestMapping("/api/users") // Base path for all endpoints in this controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity<List<User>> createUsers(@Valid @RequestBody List<User> users) {
        // @Valid triggers validation annotations on each User object in the list
        // @RequestBody maps the JSON array in the request body to a List<User>
        List<User> savedUsers = userRepository.saveAll(users); // Saves all users to the database in a batch
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsers); // Returns 201 Created with the list of saved users
    }


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Retrieves all users from the database
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // @PathVariable extracts the ID from the URL path
        Optional<User> user = userRepository.findById(id); // Attempts to find user by ID
        return user.map(ResponseEntity::ok) // If user is present, return 200 OK with user
                .orElse(ResponseEntity.notFound().build()); // Otherwise, return 404 Not Found
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        // Find the existing user by ID
        return userRepository.findById(id)
                .map(existingUser -> {
                    // Update the existing user's details
                    existingUser.setName(userDetails.getName());
                    existingUser.setEmail(userDetails.getEmail());
                    // Save the updated user back to the database
                    User updatedUser = userRepository.save(existingUser);
                    return ResponseEntity.ok(updatedUser); // Return 200 OK with the updated user
                })
                .orElse(ResponseEntity.notFound().build()); // If user not found, return 404 Not Found
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // Check if the user exists before deleting
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id); // Delete the user
            return ResponseEntity.noContent().build(); // Return 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // If user not found, return 404 Not Found
        }
    }

    @GetMapping("page")
    public Page<User> getUsers(Pageable pageable) {
        // Spring Data JPA's findAll method can accept a Pageable object
        // This automatically applies pagination and sorting based on query parameters
        return userRepository.findAll(pageable);
    }
}
