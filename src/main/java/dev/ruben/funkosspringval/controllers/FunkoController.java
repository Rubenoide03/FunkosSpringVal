package dev.ruben.funkosspringval.controllers;

import dev.ruben.funkosspringval.models.Funko;
import dev.ruben.funkosspringval.models.Model;
import dev.ruben.funkosspringval.repositories.FunkosRepository;
import dev.ruben.funkosspringval.services.FunkoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funkos")
@Slf4j
public class FunkoController {




    FunkoService funkoService;

    @Autowired
    public FunkoController(FunkoService funkoService) {
        this.funkoService = funkoService;
        funkoService.putFunko(new Funko(1L, "Spiderman", 10, 5, "Spiderman", Model.MARVEL, LocalDateTime.now(), LocalDateTime.now()));
        funkoService.putFunko(new Funko(2L, "Batman", 20, 5, "Batman", Model.OTROS, LocalDateTime.now(), LocalDateTime.now()));
    }

    @GetMapping("")
    public ResponseEntity<List<Funko>> getAllFunkos() {
        log.info("Getting all funkos");
        return ResponseEntity.ok(funkoService.getAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Funko> getFunkoById(@PathVariable Long id) {
        log.info("Getting funko by id");
        return ResponseEntity.ok(funkoService.getFunkoById(id).get());

    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAll(){
        log.info("Deleting all funkos");
        funkoService.deleteAll();
        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFunkoById(@PathVariable Long id){
        log.info("Deleting funko by id");
        funkoService.deleteFunkoById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Funko> updateFunko(@PathVariable Long id, @Valid @RequestBody Funko funko){
        log.info("Updating funko");
        funkoService.update(id,funko);
        return ResponseEntity.status(HttpStatus.CREATED).body(funko);

    }
    @PostMapping("")
    public ResponseEntity<Funko> createFunko(@Valid @RequestBody Funko funko){
        log.info("Creating funko");
        return ResponseEntity.status(HttpStatus.CREATED).body(funko);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }






}
