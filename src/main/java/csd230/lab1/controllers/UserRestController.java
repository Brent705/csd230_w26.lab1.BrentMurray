package csd230.lab1.controllers;

import csd230.lab1.entities.UserEntity;
import csd230.lab1.repositories.UserEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User REST API", description = "JSON API for managing users")
@RestController
@RequestMapping("/api/rest/users")
public class UserRestController {

    private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserRestController(UserEntityRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Get a list of all users")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<UserEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "201", description = "Successfully created user")
    @PostMapping
    public UserEntity newUser(@RequestBody UserEntity newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return repository.save(newUser);
    }

    @Operation(summary = "Get a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public UserEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Operation(summary = "Update an existing user or create a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated user"),
            @ApiResponse(responseCode = "201", description = "Successfully created user")
    })
    @PutMapping("/{id}")
    public UserEntity replaceUser(@RequestBody UserEntity newUser, @PathVariable Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    }
                    user.setRole(newUser.getRole());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    return repository.save(newUser);
                });
    }

    @Operation(summary = "Delete a user by their ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted user")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
