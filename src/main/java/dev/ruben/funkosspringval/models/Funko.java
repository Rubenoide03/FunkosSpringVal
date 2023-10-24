package dev.ruben.funkosspringval.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
public class Funko {

    private Long id;
    @NotEmpty (message = "El nombre no puede estar vacio")
    private String name;
    @Positive (message = "El precio debe ser positivo")
    private double price;
    @PositiveOrZero (message = "El stock debe ser positivo o cero")
    private int stock;
    private String image;
    @NotNull (message = "La categoria no puede ser nula")
    private Model categoria;
    @PastOrPresent (message = "La fecha de creacion debe ser pasada o presente")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
