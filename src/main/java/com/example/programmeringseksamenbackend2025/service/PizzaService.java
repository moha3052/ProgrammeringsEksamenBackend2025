package com.example.programmeringseksamenbackend2025.service;

import com.example.programmeringseksamenbackend2025.entity.Pizza;
import com.example.programmeringseksamenbackend2025.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizza(){
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> getAllPizzaById(Long id){
        return pizzaRepository.findById(id);
    }

    public Pizza createPizza(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Long id, Pizza updatePizza){
        Pizza existingPizza = pizzaRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Pizza blev ikke fundet"));

        existingPizza.setName(updatePizza.getName());
        existingPizza.setPris(updatePizza.getPris());
        existingPizza.setLeveringer(updatePizza.getLeveringer());

        return pizzaRepository.save(existingPizza);
    }

    public void deletePizza(Long id){
        pizzaRepository.deleteById(id);
    }
}
