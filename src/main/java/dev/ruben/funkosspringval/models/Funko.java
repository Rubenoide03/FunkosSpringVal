package dev.ruben.funkosspringval.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data

public class Funko {
    private long id;
    private String name;
    private double price;
    private int stock;
    private String image;
    private Model model;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
