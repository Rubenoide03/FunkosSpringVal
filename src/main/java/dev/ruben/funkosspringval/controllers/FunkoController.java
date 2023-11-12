package dev.ruben.funkosspringval.controllers;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.exceptions.FunkoNotFoundException;
import dev.ruben.funkosspringval.models.Funko;
import dev.ruben.funkosspringval.models.Model;
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
import java.util.*;

@RestController
@RequestMapping("/api/funkos")
@Slf4j
public class FunkoController {


    FunkoService funkoService;

    @Autowired
    public FunkoController(FunkoService funkoService) {
        this.funkoService = funkoService;
        funkoService.postFunko(new FunkoDTOResponse(1L, "Spiderman", 10.0, 5, "Spiderman", Model.MARVEL));
        funkoService.postFunko(new FunkoDTOResponse(2L, "Batman", 20.0, 5, "Batman", Model.OTROS));

    }


    @GetMapping("")
    public ResponseEntity<List<FunkoDTOResponse>> getAllFunkos() {
        log.info("Getting all funkos");
        return ResponseEntity.ok(funkoService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<FunkoDTOResponse> getFunkoById(@PathVariable Long id) {

        log.info("Getting funko by id");
        try {
            FunkoDTOResponse funko = funkoService.getFunkoById(id);
            return ResponseEntity.ok(funkoService.getFunkoById(id)
            );

        } catch (FunkoNotFoundException e) {
            throw new FunkoNotFoundException("Funko con id " + id + " no encontrado");
        }


    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAll() {
        log.info("Deleting all funkos");
        funkoService.deleteAll();
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFunkoById(@PathVariable Long id) {
        log.info("Deleting funko by id");
        funkoService.deleteFunkoById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funko> updateFunko(@PathVariable Long id, @Valid @RequestBody FunkoDTOResponse funko) {
        log.info("Updating funko");

        funkoService.update(id, funko);
        return ResponseEntity.ok(funkoService.update(id, funko));


    }

    @PostMapping("")
    public ResponseEntity<Funko> postFunko(@Valid @RequestBody FunkoDTOResponse funko) {
        log.info("Posting funko");
        return ResponseEntity.status(HttpStatus.CREATED).body(funkoService.postFunko(funko).get());


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
