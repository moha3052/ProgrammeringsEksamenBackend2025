package com.example.programmeringseksamenbackend2025.service;

import com.example.programmeringseksamenbackend2025.entity.Drone;
import com.example.programmeringseksamenbackend2025.entity.Levering;
import com.example.programmeringseksamenbackend2025.entity.Pizza;
import com.example.programmeringseksamenbackend2025.enums.Driftsstatus;
import com.example.programmeringseksamenbackend2025.repository.DroneRepository;
import com.example.programmeringseksamenbackend2025.repository.LeveringRepository;
import com.example.programmeringseksamenbackend2025.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeveringService {
    private final LeveringRepository leveringRepository;
    private final DroneRepository droneRepository;
    private final PizzaRepository pizzaRepository;

    public LeveringService(LeveringRepository leveringRepository, DroneRepository droneRepository, PizzaRepository pizzaRepository){
        this.leveringRepository = leveringRepository;
        this.droneRepository = droneRepository;
        this.pizzaRepository = pizzaRepository;
    }

    /**
     * Henter alle leveringer fra databasen.
     * @return Liste over alle leveringer.
     */
    public List<Levering> getAllLevering(){
        return leveringRepository.findAll();
    }

    public Optional<Levering> getAllLeveringById(Long id){
        return leveringRepository.findById(id);
    }

    /**
     * Henter alle leveringer, der ikke er afsluttet (ingen faktisk leveringstid sat).
     * @return Liste over uafsluttede leveringer.
     */
    public List<Levering> getUnfinishedLeveringer(){
        return leveringRepository.findByfaktiskLeveringIsNull();
    }

    /**
     * Opretter en ny levering med en forventet leveringstid 30 minutter fra nu.
     * Dronen sættes som null, da den ikke tildeles ved oprettelsen.
     * @param levering Leveringsobjekt, der skal oprettes.
     * @return Den oprettede levering.
     */
    public Levering createLevering(Levering levering){
        // Valider pizzaen
        Long pizzaId = levering.getPizza().getId();
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new RuntimeException("Pizza med ID " + pizzaId + " findes ikke i databasen"));

        // Tildel den persistente pizza til leveringen
        levering.setPizza(pizza);

        // Sæt andre nødvendige felter
        levering.setForventet_levering(LocalTime.now().plusMinutes(30));
        levering.setDrone(null);

        return leveringRepository.save(levering);
    }

    /**
     * Henter alle leveringer, der ikke har en tildelt drone.
     * @return Liste over leveringer uden drone.
     */
    public List<Levering> getQueuedLeveringer (){
        return leveringRepository.findByDroneIsNull();
    }

    public Levering updateLeveringById(Long id, Levering updateLevering){
        Levering existingLevering = leveringRepository.findById(id)
                .orElseThrow( () -> new RuntimeException());

        existingLevering.setFaktisk_levering(updateLevering.getFaktisk_levering());
        existingLevering.setForventet_levering(updateLevering.getForventet_levering());
        existingLevering.setAdresse(updateLevering.getAdresse());
        existingLevering.setDrone(updateLevering.getDrone());
        existingLevering.setPizza(updateLevering.getPizza());

        return leveringRepository.save(existingLevering);
    }

    /**
     * Tildeler en drone til en levering, der mangler en drone.
     * Validerer, at dronen er "i drift", og at leveringen ikke allerede har en drone.
     * @param leveringId ID for den levering, der skal opdateres.
     * @param droneId ID for dronen, der skal tildeles.
     * @return Opdateret levering med tildelt drone.
     */
    public Levering scheduleLevering(Long droneId, Long leveringId){
        Levering levering = leveringRepository.findById(leveringId)
                .orElseThrow(() -> new RuntimeException("Levering ikke fundet"));

        if (levering.getDrone() != null){
            throw new RuntimeException("Levering har allerede en drone");
        }

        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new RuntimeException("Drone ikke fundet"));

        if (drone.getDriftsstatus() != Driftsstatus.I_DRIFT){
            throw new RuntimeException("Drone er ikke i drift");
        }

        levering.setDrone(drone);
        return leveringRepository
                .save(levering);
    }

    /**
     * Marker en levering som færdig ved at sætte den faktiske leveringstid til nu.
     * Validerer, at leveringen har en tildelt drone.
     * @param id ID for den levering, der skal afsluttes.
     * @return Opdateret levering med faktisk leveringstid.
     */
    public Levering finishLevering(Long id){
        Levering levering = leveringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Levering ikke fundet"));

        if (levering.getDrone() == null){
            throw new RuntimeException("Levering har ingen drone");
        }

        levering.setFaktisk_levering(LocalTime.now());
        return leveringRepository.save(levering);
    }

    public void deleteLevering(Long id){
        leveringRepository.deleteById(id);
    }
}
