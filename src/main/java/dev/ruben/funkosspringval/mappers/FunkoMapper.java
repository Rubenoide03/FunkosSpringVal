package dev.ruben.funkosspringval.mappers;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.models.Funko;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class FunkoMapper {

    public FunkoDTOResponse toDTO(Funko funko){
        return FunkoDTOResponse.builder()
                .id(funko.getId())
                .name(funko.getName())
                .price(funko.getPrice())
                .stock(funko.getStock())
                .image(funko.getImage())
                .model(funko.getModel())
                .build();


    }
    public List<FunkoDTOResponse> ListtoDTO(List<Funko> funkos){
        return funkos.stream().map(this::toDTO).toList();




    }

    public Funko toFunko(FunkoDTOResponse funkoDTOResponse) {
        return Funko.builder()
                .id(funkoDTOResponse.getId())
                .name(funkoDTOResponse.getName())
                .price(funkoDTOResponse.getPrice())
                .stock(funkoDTOResponse.getStock())
                .image(funkoDTOResponse.getImage())
                .model(funkoDTOResponse.getModel())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }







}
