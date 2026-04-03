package csd230.lab1.controllers;

import csd230.lab1.entities.DiscMagEntity;
import csd230.lab1.repositories.DiscMagEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Disc Magazine REST API", description = "JSON API for managing magazines with discs")
@RestController
@RequestMapping("/api/rest/discmags")
public class DiscMagRestController {

    private final DiscMagEntityRepository repository;

    public DiscMagRestController(DiscMagEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get a list of all disc magazines")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<DiscMagEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new disc magazine")
    @ApiResponse(responseCode = "201", description = "Successfully created disc magazine")
    @PostMapping
    public DiscMagEntity newDiscMag(@RequestBody DiscMagEntity newDiscMag) {
        return repository.save(newDiscMag);
    }

    @Operation(summary = "Get a disc magazine by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the disc magazine"),
            @ApiResponse(responseCode = "404", description = "Disc magazine not found")
    })
    @GetMapping("/{id}")
    public Optional<DiscMagEntity> one(@PathVariable Long id) {
        return repository.findById(id);
    }

    @Operation(summary = "Update an existing disc magazine or create a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated disc magazine"),
            @ApiResponse(responseCode = "201", description = "Successfully created disc magazine")
    })
    @PutMapping("/{id}")
    public DiscMagEntity replaceDiscMag(@RequestBody DiscMagEntity newDiscMag, @PathVariable Long id) {
        return repository.findById(id)
                .map(discMag -> {
                    discMag.setTitle(newDiscMag.getTitle());
                    discMag.setPrice(newDiscMag.getPrice());
                    discMag.setCopies(newDiscMag.getCopies());
                    discMag.setOrderQty(newDiscMag.getOrderQty());
                    discMag.setCurrentIssue(newDiscMag.getCurrentIssue());
                    discMag.setHasDisc(newDiscMag.isHasDisc());
                    return repository.save(discMag);
                })
                .orElseGet(() -> {
                    newDiscMag.setId(id);
                    return repository.save(newDiscMag);
                });
    }

    @Operation(summary = "Delete a disc magazine by its ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted disc magazine")
    @DeleteMapping("/{id}")
    public void deleteDiscMag(@PathVariable Long id) {
        repository.deleteById(id);
    }
}