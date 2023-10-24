package dev.ruben.funkosspringval.dto;

import dev.ruben.funkosspringval.models.Model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class FunkoDTO {
    private Long id;
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;
    @Positive(message = "El precio debe ser positivo")
    private double price;
    @PositiveOrZero(message = "El stock debe ser positivo o cero")
    private int stock;
    @NotEmpty(message = "La imagen no puede estar vacia")
    private String image;
    @NotNull(message = "La categoria no puede ser nula")
    private Model categoria;



}
