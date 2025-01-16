package com.example.programmeringseksamenbackend2025.controller;

import com.example.programmeringseksamenbackend2025.entity.Pizza;
import com.example.programmeringseksamenbackend2025.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pizza")
@CrossOrigin("*")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizza(){
        List<Pizza> allPizza = pizzaService.getAllPizza();
        return ResponseEntity.ok(allPizza);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Pizza>> getAllPizzaById(@PathVariable Long id){
        Optional<Pizza> allPizzaById = pizzaService.getAllPizzaById(id);
        return ResponseEntity.ok(allPizzaById);
    }

    @PostMapping
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza){
        Pizza createPizza = pizzaService.createPizza(pizza);
        return new ResponseEntity<>(createPizza, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Pizza> updatePizzaById(@PathVariable Long id, @RequestBody Pizza updatePizza) {
        try {
            Pizza updatedPizza = pizzaService.updatePizzaById(id, updatePizza);
            return ResponseEntity.ok(updatedPizza); // Returnerer HTTP 200 med den opdaterede pizza i body
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Returnerer HTTP 404, hvis pizzaen ikke findes
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePizzaById(@PathVariable Long id){
        try {
            pizzaService.deletePizza(id); // Forsøger at slette pizzaen
            return ResponseEntity.noContent().build(); // Returnerer HTTP 204, hvis sletningen lykkes
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Returnerer HTTP 404, hvis pizzaen ikke findes
        }
    }


}
