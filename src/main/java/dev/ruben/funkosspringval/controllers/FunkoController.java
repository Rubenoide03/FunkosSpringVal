package dev.ruben.funkosspringval.controllers;

import dev.ruben.funkosspringval.models.Funko;
import dev.ruben.funkosspringval.models.Model;
import dev.ruben.funkosspringval.repositories.FunkosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/funkos")
public class FunkoController {
    List<Funko> funkos;



    FunkosRepository funkosRepository;

    @Autowired
    public FunkoController(FunkosRepository funkosRepository) {


        this.funkosRepository = funkosRepository;
        this.funkos= funkos;
        funkos.add(new Funko(1L, "Funatic", 20, 5, "Spiderman", Model.MARVEL, LocalDateTime.now(), LocalDateTime.now()));
        funkos.add(new Funko(2L, "Funatic", 20, 5, "Batman", Model.OTROS, LocalDateTime.now(), LocalDateTime.now()));
    }

    @GetMapping("/funkos")
    public ResponseEntity<List<Funko>> getAllFunkos() {
        return ResponseEntity.ok(funkosRepository.getAll());

    }

    @GetMapping("/funkos/{id}")
    public ResponseEntity<Funko> getFunkoById(@PathVariable Long id) {
        return ResponseEntity.ok(funkosRepository.getById(id).get());

    }



}
