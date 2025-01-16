package com.example.programmeringseksamenbackend2025.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double pris;

    @OneToMany(mappedBy = "pizza")
    private List<Levering> leveringer; // En pizza kan være del af flere leveringer

    public Pizza(Long id, String name, double pris) {
        this.id = id;
        this.name = name;
        this.pris = pris;
    }

    public Pizza(){

    }

}
