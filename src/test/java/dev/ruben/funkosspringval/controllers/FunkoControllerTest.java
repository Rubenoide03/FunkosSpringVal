package dev.ruben.funkosspringval.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.exceptions.FunkoNotAvailableAddException;
import dev.ruben.funkosspringval.models.Funko;
import dev.ruben.funkosspringval.models.Model;
import dev.ruben.funkosspringval.services.FunkoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureJsonTesters
@ExtendWith(MockitoExtension.class)
class FunkoControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private final String endPoint = "/api/funkos";

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private FunkoService funkoService;
    @Autowired
    private JacksonTester<FunkoDTOResponse> jsonFunkoDto;
    private final FunkoDTOResponse funko1 = FunkoDTOResponse.builder()
            .id(1L)
            .name("Funko1")
            .price(10.0)
            .stock(10)
            .image("image")
            .model(Model.ANIME)
            .build();
    private final Funko funkoF = Funko.builder()
            .id(1L)
            .name("Funko1")
            .price(10.0)
            .stock(10)
            .image("image")
            .model(Model.ANIME)
            .build();
    private final FunkoDTOResponse funko2 = FunkoDTOResponse.builder()
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
        when(funkoService.getAll()).thenReturn(funkoLista);
        mockMvc.perform(get(endPoint)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        assertEquals(2, funkoLista.size());
    }


    @Test
    void getFunkoById() throws Exception {
        var funkoADevolver = funko1;
        Long id = 1L;
        when(funkoService.getFunkoById(id)).thenReturn(funkoADevolver);
        mockMvc.perform(get(endPoint + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        assertEquals(1L, funkoADevolver.getId());

    }

    @Test
    void deleteAll() throws Exception {
        doNothing().when(funkoService).deleteAll();
        mockMvc.perform(MockMvcRequestBuilders.delete(endPoint))
                .andExpect(status().isNoContent());
        Mockito.verify(funkoService, times(1)).deleteAll();
        assertEquals(0, funkoService.getAll().size());


    }

    @Test
    void deleteFunkoById() throws Exception {
        var endPointTest = endPoint + "/{1}";
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete(endPoint + "/{id}", id))
                .andExpect(status().isNoContent());
        Mockito.verify(funkoService, times(1)).deleteFunkoById(id);
        assertEquals(0, funkoService.getAll().size());
        assertNull(funkoService.getFunkoById(id));


    }





    @Test
    void updateFunko() throws Exception {
        Long id = 1L;
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.
                put(endPoint + "/{id}", id).contentType(MediaType.APPLICATION_JSON).content(
                        jsonFunkoDto.write(new FunkoDTOResponse(1L, "Funko1", 10.0, 10, "image", Model.ANIME)).getJson()
                )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(   1L, funko1.getId());
    }






}

