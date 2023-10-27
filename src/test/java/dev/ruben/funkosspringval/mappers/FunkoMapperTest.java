package dev.ruben.funkosspringval.mappers;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.models.Funko;
import dev.ruben.funkosspringval.models.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FunkoMapperTest {
    @Autowired
    private FunkoMapper funkoMapper;

    @Test
    void toDTO() {
        Long id=1L;
        FunkoDTOResponse funkoDTOResponse =new FunkoDTOResponse(
                id,
                "Funko1",
                10.0,
                10,
                "image",
                Model.ANIME
        );
        assertEquals(id, funkoDTOResponse.getId());
        assertEquals("Funko1", funkoDTOResponse.getName());
        assertEquals(10.0, funkoDTOResponse.getPrice());
        assertEquals(10, funkoDTOResponse.getStock());
        assertEquals("image", funkoDTOResponse.getImage());
        assertEquals(Model.ANIME, funkoDTOResponse.getModel());
    }

    @Test
    void testToDTO() {

}

    @Test
    void toFunko() {
        Long id=1L;
        FunkoDTOResponse funkoDTOResponse =new FunkoDTOResponse(
                id,
                "Funko1",
                10.0,
                10,
                "image",
                Model.ANIME
        );
        Funko funko = new Funko(
                id,
                "Funko1",
                10.0,
                10,
                "image",
                Model.ANIME,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        assertEquals(id, funko.getId());
        assertEquals("Funko1", funko.getName());
        assertEquals(10.0, funko.getPrice());
        assertEquals(10, funko.getStock());
        assertEquals("image", funko.getImage());
        assertEquals(Model.ANIME, funko.getModel());

    }
}