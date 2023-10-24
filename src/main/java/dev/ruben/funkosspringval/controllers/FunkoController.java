package dev.ruben.funkosspringval.controllers;

import dev.ruben.funkosspringval.dto.FunkoDTO;
import dev.ruben.funkosspringval.exceptions.FunkoNotFoundException;
import dev.ruben.funkosspringval.exceptions.IdNotValidException;
import dev.ruben.funkosspringval.models.Funko;
import dev.ruben.funkosspringval.models.Model;
import dev.ruben.funkosspringval.services.FunkoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/funkos")
@CacheConfig(cacheNames = "funkos")
@Slf4j
public class FunkoController {




    FunkoService funkoService;

    @Autowired
    public FunkoController(FunkoService funkoService) {
        this.funkoService = funkoService;
        funkoService.postFunko(new Funko(1L, "Spiderman", 10, 5, "Spiderman", Model.MARVEL, LocalDateTime.now(), LocalDateTime.now()));
        funkoService.postFunko(new Funko(2L, "Batman", 20, 5, "Batman", Model.OTROS, LocalDateTime.now(), LocalDateTime.now()));
    }

@Cacheable
    @GetMapping("")
    public ResponseEntity<List<FunkoDTO>> getAllFunkos() {
        log.info("Getting all funkos");
        return ResponseEntity.ok(funkoService.getAll());

    }
    @Cacheable
    @GetMapping("/{id}")
    public ResponseEntity<FunkoDTO> getFunkoById(@PathVariable Long id) {

        try {
            log.info("Getting funko by id");
            return ResponseEntity.ok(funkoService.getFunkoById(id).orElseThrow(
                    () -> new FunkoNotFoundException("Funko not found")
            ));
        } catch (IdNotValidException e) {
            throw new IdNotValidException("Id not valid");
        }
    }



    @CacheEvict
    @DeleteMapping("")
    public ResponseEntity<Void> deleteAll(){
        log.info("Deleting all funkos");
        funkoService.deleteAll();
        return ResponseEntity.noContent().build();

    }
    @CacheEvict
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFunkoById(@PathVariable Long id){
        log.info("Deleting funko by id");
        funkoService.deleteFunkoById(id);

        return ResponseEntity.noContent().build();
    }
    @CachePut
    @PutMapping("/{id}")
    public ResponseEntity<FunkoDTO> updateFunko(@PathVariable Long id, @Valid @RequestBody Funko funko){
        log.info("Updating funko");
        funkoService.update(id,funko);
        ResponseEntity.status(HttpStatus.CREATED).body(funko);
        return ResponseEntity.ok(funkoService.getFunkoById(id).orElseThrow((
                () -> new FunkoNotFoundException("Funko not found"))
        ));

    }
    @Cacheable
    @PostMapping("")
    public ResponseEntity<Funko> postFunko(@Valid @RequestBody Funko funko){
        log.info("Posting funko");
        funkoService.postFunko(funko);
        return ResponseEntity.status(HttpStatus.CREATED).body(funko);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }






}
