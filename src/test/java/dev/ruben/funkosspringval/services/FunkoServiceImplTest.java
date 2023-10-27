package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.mappers.FunkoMapper;
import dev.ruben.funkosspringval.models.Model;
import dev.ruben.funkosspringval.repositories.FunkosRepository;
import dev.ruben.funkosspringval.repositories.FunkosRepositoryImpl;
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import dev.ruben.funkosspringval.services.FunkoServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FunkoServiceImplTest {
    final FunkoDTOResponse funko1 = new FunkoDTOResponse(1L, "Funko1", 10.0, 10, "image", Model.ANIME);


    @Mock
    private FunkosRepositoryImpl funkosRepository;

    @Mock
    private FunkoMapper funkoMapper;
    @InjectMocks
    private FunkoServiceImpl funkoService;

    @Test
    void getAll() {
        List<FunkoDTOResponse> previousFunkos = List.of(funko1);
        Mockito.when(funkoService.getAll()).thenReturn(previousFunkos);
        List<FunkoDTOResponse> funkos = funkoService.getAll();
        assertEquals(1, funkos.size());
        Mockito.when(funkosRepository.getAll()).thenReturn(funkos);
        Mockito.verify(funkosRepository, Mockito.times(1)).getAll();

    }

    @Test
    void getFunkoById() {
        Long id = 1L;
        Mockito.when(funkoService.getFunkoById(Mockito.anyLong())).thenReturn(funko1);
        FunkoDTOResponse funko = funkoService.getFunkoById(id);
        assertEquals(1L, funko.getId());
        Mockito.when(funkosRepository.getById(id)).thenReturn(funko);
        Mockito.verify(funkosRepository, Mockito.times(1)).getById(Mockito.anyLong());
    }

    @Test
    void postFunko() {
        Mockito.when(funkoService.postFunko(funko1));
        funkoService.postFunko(funko1);
        Mockito.verify(funkosRepository, Mockito.times(1)).put(funko1);
    }

    @Test
    void deleteFunkoById() {
        Long id = 1L;
        funkoService.deleteFunkoById(id);
        Mockito.verify(funkosRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void deleteAll() {
        funkoService.deleteAll();
        Mockito.verify(funkosRepository, Mockito.times(1)).deleteAll();


    }

    @Test
    void update() {
        Mockito.when(funkoService.update(1L, funko1));
        funkoService.update(1L, funko1);
        Mockito.verify(funkosRepository, Mockito.times(1)).update(1L, funko1);


    }
}