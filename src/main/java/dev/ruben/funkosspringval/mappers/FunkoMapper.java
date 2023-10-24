package dev.ruben.funkosspringval.mappers;

import dev.ruben.funkosspringval.dto.FunkoDTO;
import dev.ruben.funkosspringval.models.Funko;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FunkoMapper {
    public FunkoDTO toFunkoDTO(Funko funko) {
        return new FunkoDTO(
                funko.getId(),
                funko.getName(),
                funko.getPrice(),
                funko.getStock(),
                funko.getImage(),
                funko.getCategoria()

        );
    }
    public Funko toFunko(FunkoDTO funkoDTO, Funko f) {
        return new Funko(
                f.getId(),
                funkoDTO.getName() != null ? funkoDTO.getName() : f.getName(),
                funkoDTO.getPrice() != null ? funkoDTO.getPrice() : f.getPrice(),
                funkoDTO.getStock() != null ? funkoDTO.getStock() : f.getStock(),
                funkoDTO.getImage() != null ? funkoDTO.getImage() : f.getImage(),
                funkoDTO.getCategoria() != null ? funkoDTO.getCategoria() : f.getCategoria(),
                f.getCreatedAt(),
                f.getUpdatedAt()
        );
    }
}