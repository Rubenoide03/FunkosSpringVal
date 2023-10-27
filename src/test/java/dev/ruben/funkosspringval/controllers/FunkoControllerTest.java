package dev.ruben.funkosspringval.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.models.Model;
import dev.ruben.funkosspringval.services.FunkoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureJsonTesters
@ExtendWith(MockitoExtension.class)
class FunkoControllerTest {

    private final ObjectMapper mapper=new ObjectMapper();

    private final String endPoint="/api/funkos";

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private FunkoService funkoService;
    @Autowired
    private JacksonTester<FunkoDTOResponse> jsonFunkoDto;
    private final FunkoDTOResponse funko1= FunkoDTOResponse.builder()
            .id(1L)
            .name("Funko1")
            .price(10.0)
            .stock(10)
            .image("image")
            .model(Model.ANIME)
            .build();
    private final FunkoDTOResponse funko2=FunkoDTOResponse.builder()
            .id(1L)
            .name("Funko2")
            .price(40.0)
            .stock(30)
            .image("image")
            .model(Model.ANIME)
            .build();

    @Autowired
    public FunkoControllerTest(FunkoService funkoService) {
        this.funkoService = funkoService;
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getAllFunkos() throws Exception {
        var funkoLista = List.of(funko1, funko2);
        Mockito.when(funkoService.getAll()).thenReturn(funkoLista);
        mockMvc.perform(get(endPoint)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getFunkoById() {
        var funkoADevolver = funko1;
        Long id = 1L;
        Mockito.when(funkoService.getFunkoById(id)).thenReturn(funkoADevolver);

    }

    @Test
    void deleteAll() {
    }

    @Test
    void deleteFunkoById() {
    }

    @Test
    void updateFunko() {
    }

    @Test
    void postFunko() {
    }
}