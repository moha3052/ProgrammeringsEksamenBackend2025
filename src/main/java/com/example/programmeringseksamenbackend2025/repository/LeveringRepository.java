package com.example.programmeringseksamenbackend2025.repository;

import com.example.programmeringseksamenbackend2025.entity.Levering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeveringRepository extends JpaRepository<Levering, Long> {
}
