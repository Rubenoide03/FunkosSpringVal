package dev.ruben.funkosspringval.dto;

import dev.ruben.funkosspringval.models.Model;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder

public class FunkoDTOResponse {

    private Long id;
    @NotEmpty
    private String name;
    @Positive
    private Double price;
    @PositiveOrZero
    private Integer stock;
    private String image;
    private Model model;
}
