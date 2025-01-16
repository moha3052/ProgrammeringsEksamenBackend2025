package com.example.programmeringseksamenbackend2025.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int pris;

    @OneToMany(mappedBy = "pizza")
    private List<Levering> leveringer; // En pizza kan være del af flere leveringer

    public Pizza(Long id, String name, int pris) {
        this.id = id;
        this.name = name;
        this.pris = pris;
    }

    public Pizza(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public List<Levering> getLeveringer() {
        return leveringer;
    }

    public void setLeveringer(List<Levering> leveringer) {
        this.leveringer = leveringer;
    }
}
