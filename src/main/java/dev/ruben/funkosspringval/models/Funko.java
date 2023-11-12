package dev.ruben.funkosspringval.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
@Entity

public class Funko {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @NotNull (message = "El id no puede ser nulo")
    private Long id;
    @NotEmpty (message = "El nombre no puede estar vacio")
    private String name;
    @Positive (message = "El precio debe ser positivo")
    private Double price;
    @PositiveOrZero (message = "El stock debe ser positivo o cero")
    private Integer stock;
    private String image;
    @NotNull (message = "El modelo no puede ser nulo")
    private Model model;
    @NotBlank
    @PastOrPresent (message = "La fecha de creacion debe ser pasada o presente")
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public  Funko(){

    }
}
