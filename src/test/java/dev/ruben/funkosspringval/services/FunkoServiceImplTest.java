package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.mappers.FunkoMapper;
import dev.ruben.funkosspringval.models.Model;
import dev.ruben.funkosspringval.repositories.FunkosRepository;
import dev.ruben.funkosspringval.repositories.FunkosRepositoryImpl;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class FunkoServiceImplTest {

    @Mock
    private FunkoMapper funkoMapper;
@InjectMocks
private FunkoService funkoService = new FunkoServiceImpl(funkosRepository, funkoMapper);




    @BeforeEach
     void setUp() {
         List<FunkoDTOResponse> funkos = new ArrayList<>();
            FunkoDTOResponse funko1 = new FunkoDTOResponse(1L, "Funko1", 10.0, 10, "image", Model.ANIME);
            FunkoDTOResponse funko2 = new FunkoDTOResponse(2L, "Funko2", 10.0, 10, "image", Model.ANIME);
            funkos.add(funko1);
            funkos.add(funko2);
         Mockito.when(funkosRepository.getAll()).thenReturn(funkos);

     }


    @Test
    void getAll() {
        List<FunkoDTOResponse> funkos = funkoService.getAll();
        assertEquals(2, funkos.size());

    }

    @Test
    void getFunkoById() {
    }

    @Test
    void postFunko() {
    }

    @Test
    void deleteFunkoById() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void update() {
    }
}