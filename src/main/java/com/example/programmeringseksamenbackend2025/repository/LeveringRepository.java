package com.example.programmeringseksamenbackend2025.repository;

import com.example.programmeringseksamenbackend2025.entity.Levering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeveringRepository extends JpaRepository<Levering, Long> {

    /**
     * Finder alle leveringer, hvor den faktiske leveringstid er null
     * (leveringer, der ikke er afsluttet endnu).
     * @return Liste over uafsluttede leveringer.
     */
    List<Levering> findByfaktiskLeveringIsNull();

    List<Levering> findByDroneIsNull();
}
