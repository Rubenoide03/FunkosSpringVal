package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.exceptions.FunkoNotFoundException;
import dev.ruben.funkosspringval.models.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import dev.ruben.funkosspringval.repositories.FunkosRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FunkosRepositoryImplTest {
    @Autowired
    private FunkosRepository funkosRepository;
    final FunkoDTOResponse funko1 = new FunkoDTOResponse(1L, "Funko1", 10.0, 10, "image", Model.ANIME);
    final FunkoDTOResponse funko2 = new FunkoDTOResponse(2L, "Funko2", 10.0, 10, "image", Model.ANIME);

    @BeforeEach
    void setUp() {

        funkosRepository = new FunkosRepositoryImpl();
        funkosRepository.put(funko1);
        funkosRepository.put(funko2);



    }

    @Test
    void getAll() {
        List<FunkoDTOResponse> funkos = funkosRepository.getAll();
        assertEquals(2, funkos.size());


    }

    @Test
    void getById() {
        FunkoDTOResponse funko = funkosRepository.getById(1L);
        assertEquals(1L, funko.getId());
    }

    @Test
    void put() {
        FunkoDTOResponse funko = new FunkoDTOResponse(3L, "Funko3", 10.0, 10, "image", Model.ANIME);
        funkosRepository.put(funko);
        var all = funkosRepository.getAll();
        assertAll(
                () -> assertNotNull(funkosRepository),
                () -> assertEquals(3, all.size()));


    }


    @Test
    void deleteById() {
        FunkoDTOResponse funko = funkosRepository.getById(1L);
        funkosRepository.deleteById(1L);
        assertEquals(1, funkosRepository.getAll().size());
        assertThrowsExactly(FunkoNotFoundException.class, () -> funkosRepository.getById(1L));
    }

    @Test
    void deleteAll() {
        funkosRepository.deleteAll();

        assertEquals(0, funkosRepository.getAll().size());


    }


    @Test
    void update() {
        FunkoDTOResponse funko = new FunkoDTOResponse(1L, "Funko1", 10.0, 10, "image", Model.ANIME);
        funkosRepository.update(1L, funko);
        assertEquals(1L, funko.getId());
        assertEquals("Funko1", funko.getName());
        assertEquals(10.0, funko.getPrice());
        assertEquals(10, funko.getStock());
        assertEquals("image", funko.getImage());
        assertEquals(Model.ANIME, funko.getModel());
    }
}