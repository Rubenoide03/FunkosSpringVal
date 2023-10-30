package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.exceptions.FunkoNotAvailableAddException;
import dev.ruben.funkosspringval.mappers.FunkoMapper;
import dev.ruben.funkosspringval.models.Funko;
import dev.ruben.funkosspringval.models.Model;
import dev.ruben.funkosspringval.repositories.FunkosRepository;
import dev.ruben.funkosspringval.repositories.FunkosRepositoryImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import dev.ruben.funkosspringval.services.FunkoServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

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
        Mockito.verify(funkosRepository, times(1)).getAll();

    }

    @Test
    void getFunkoById() {
        Long id = 1L;
        Mockito.when(funkoService.getFunkoById(Mockito.anyLong())).thenReturn(funko1);
        FunkoDTOResponse funko = funkoService.getFunkoById(id);
        assertEquals(1L, funko.getId());
        Mockito.when(funkosRepository.getById(id)).thenReturn(funko);
        Mockito.verify(funkosRepository, times(1)).getById(Mockito.anyLong());
    }

    @Test
    void postFunko() {
        FunkoDTOResponse newFunkoDTO = new FunkoDTOResponse(2L, "Funko2", 15.0, 5, "image2", Model.ANIME);
        Funko newFunko = new Funko(2L, "Funko2", 15.0, 5, "image2", Model.ANIME, null, null);

        Mockito.when(funkoMapper.toFunko(newFunkoDTO)).thenReturn(newFunko);
        Funko funkoCreado = funkoService.postFunko(newFunkoDTO).orElse(null);
        assertAll(
                () -> assertEquals(2L, funkoCreado.getId()),
                () -> assertEquals("Funko2", funkoCreado.getName()),
                () -> assertEquals(15.0, funkoCreado.getPrice()),
                () -> assertEquals(5, funkoCreado.getStock()),
                () -> assertEquals("image2", funkoCreado.getImage()),
                () -> assertEquals(Model.ANIME, funkoCreado.getModel()),
                () -> assertNull(funkoCreado.getCreatedAt()),
                () -> assertNull(funkoCreado.getUpdatedAt())
        );
    }


    @Test
    void deleteFunkoById() {
        Long id = 1L;
        funkoService.deleteFunkoById(id);
        Mockito.verify(funkosRepository, times(1)).deleteById(id);
        assertEquals(0, funkoService.getAll().size());
    }

    @Test
    void deleteAll() {
        funkoService.deleteAll();
        Mockito.verify(funkosRepository, times(1)).deleteAll();


    }

    @Test
    void update() {
        Long id = 1L;
        FunkoDTOResponse funkoAActualizar = new FunkoDTOResponse(1L, "UpdatedFunko", 20.0, 15, "updatedImage", Model.ANIME);

        //Mockito.when(funkoService.update(id, funkoAActualizar)).thenReturn(funkoAActualizar);

        Mockito.when(funkoMapper.toFunko(funkoAActualizar)).thenReturn(new Funko( 1L, "UpdatedFunko", 20.0, 15, "updatedImage", Model.ANIME, null, null));

        Funko result = funkoService.update(id, funkoAActualizar);

        assertEquals(funkoAActualizar.getId(), result.getId());
        Mockito.verify(funkosRepository, times(1)).update(id, new FunkoDTOResponse( 1L, "UpdatedFunko", 20.0, 15, "updatedImage", Model.ANIME));


    }
}