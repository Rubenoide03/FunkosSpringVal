package dev.ruben.funkosspringval.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data

public class Funko {
    @NotNull (message = "El id no puede ser nulo")
    private long id;
    @NotEmpty (message = "El nombre no puede estar vacio")
    private String name;
    @Positive (message = "El precio debe ser positivo")
    private double price;
    @PositiveOrZero (message = "El stock debe ser positivo o cero")
    private int stock;
    private String image;
    @NotNull (message = "El modelo no puede ser nulo")
    private Model model;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
