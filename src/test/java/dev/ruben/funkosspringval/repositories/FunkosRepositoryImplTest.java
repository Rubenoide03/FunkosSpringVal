package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.models.Funko;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class FunkosRepositoryImplTest {
    @Autowired
    private FunkosRepository funkosRepository;

    @BeforeEach
    void setUp() {
        funkosRepository.deleteAll();
        
        funkosRepository.put(funco1);
        funkosRepository.put(funco2);
        
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void put() {
    }

    @Test
    void getByName() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void update() {
    }
}