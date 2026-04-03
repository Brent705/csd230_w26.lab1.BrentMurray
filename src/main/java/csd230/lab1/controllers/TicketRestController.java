package csd230.lab1.controllers;

import csd230.lab1.entities.TicketEntity;
import csd230.lab1.repositories.TicketEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Ticket REST API", description = "JSON API for managing tickets")
@RestController
@RequestMapping("/api/rest/tickets")
public class TicketRestController {

    private final TicketEntityRepository repository;

    public TicketRestController(TicketEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get a list of all tickets")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<TicketEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new ticket")
    @ApiResponse(responseCode = "201", description = "Successfully created ticket")
    @PostMapping
    public TicketEntity newTicket(@RequestBody TicketEntity newTicket) {
        return repository.save(newTicket);
    }

    @Operation(summary = "Get a ticket by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the ticket"),
            @ApiResponse(responseCode = "404", description = "Ticket not found")
    })
    @GetMapping("/{id}")
    public TicketEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Operation(summary = "Update an existing ticket or create a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated ticket"),
            @ApiResponse(responseCode = "201", description = "Successfully created ticket")
    })
    @PutMapping("/{id}")
    public TicketEntity replaceTicket(@RequestBody TicketEntity newTicket, @PathVariable Long id) {
        return repository.findById(id)
                .map(ticket -> {
                    ticket.setDescription(newTicket.getDescription());
                    ticket.setPrice(newTicket.getPrice());
                    return repository.save(ticket);
                })
                .orElseGet(() -> {
                    newTicket.setId(id);
                    return repository.save(newTicket);
                });
    }

    @Operation(summary = "Delete a ticket by its ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted ticket")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        repository.deleteById(id);
    }
}