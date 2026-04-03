package csd230.lab1.controllers;

import csd230.lab1.entities.MagazineEntity;
import csd230.lab1.repositories.MagazineEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Magazine REST API", description = "JSON API for managing magazines")
@RestController
@RequestMapping("/api/rest/magazines")
public class MagazineRestController {

    private final MagazineEntityRepository repository;

    public MagazineRestController(MagazineEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get a list of all magazines")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<MagazineEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new magazine")
    @ApiResponse(responseCode = "201", description = "Successfully created magazine")
    @PostMapping
    public MagazineEntity newMagazine(@RequestBody MagazineEntity newMagazine) {
        return repository.save(newMagazine);
    }

    @Operation(summary = "Get a magazine by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the magazine"),
            @ApiResponse(responseCode = "404", description = "Magazine not found")
    })
    @GetMapping("/{id}")
    public MagazineEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MagazineNotFoundException(id));
    }

    @Operation(summary = "Update an existing magazine or create a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated magazine"),
            @ApiResponse(responseCode = "201", description = "Successfully created magazine")
    })
    @PutMapping("/{id}")
    public MagazineEntity replaceMagazine(@RequestBody MagazineEntity newMagazine, @PathVariable Long id) {
        return repository.findById(id)
                .map(magazine -> {
                    magazine.setTitle(newMagazine.getTitle());
                    magazine.setPrice(newMagazine.getPrice());
                    magazine.setCopies(newMagazine.getCopies());
                    magazine.setOrderQty(newMagazine.getOrderQty());
                    magazine.setCurrentIssue(newMagazine.getCurrentIssue());
                    return repository.save(magazine);
                })
                .orElseGet(() -> {
                    newMagazine.setId(id);
                    return repository.save(newMagazine);
                });
    }

    @Operation(summary = "Delete a magazine by its ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted magazine")
    @DeleteMapping("/{id}")
    public void deleteMagazine(@PathVariable Long id) {
        repository.deleteById(id);
    }
}