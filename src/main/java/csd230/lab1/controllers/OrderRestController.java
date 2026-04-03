package csd230.lab1.controllers;

import csd230.lab1.entities.OrderEntity;
import csd230.lab1.repositories.OrderEntityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order REST API", description = "JSON API for managing orders")
@RestController
@RequestMapping("/api/rest/orders")
public class OrderRestController {

    private final OrderEntityRepository repository;

    public OrderRestController(OrderEntityRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Get a list of all orders")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<OrderEntity> all() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new order")
    @ApiResponse(responseCode = "201", description = "Successfully created order")
    @PostMapping
    public OrderEntity newOrder(@RequestBody OrderEntity newOrder) {
        return repository.save(newOrder);
    }

    @Operation(summary = "Get an order by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the order"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{id}")
    public OrderEntity one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Operation(summary = "Update an existing order or create a new one")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated order"),
            @ApiResponse(responseCode = "201", description = "Successfully created order")
    })
    @PutMapping("/{id}")
    public OrderEntity replaceOrder(@RequestBody OrderEntity newOrder, @PathVariable Long id) {
        return repository.findById(id)
                .map(order -> {
                    order.setOrderDate(newOrder.getOrderDate());
                    order.setTotalAmount(newOrder.getTotalAmount());
                    order.setProducts(newOrder.getProducts());
                    return repository.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setId(id);
                    return repository.save(newOrder);
                });
    }

    @Operation(summary = "Delete an order by its ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted order")
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
