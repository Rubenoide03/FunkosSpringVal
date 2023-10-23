package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.models.Funko;

import java.util.List;
import java.util.Optional;

public interface FunkosRepository {

    List<Funko> getAll();
    Optional<Funko> getById(Long id);
    void put(Funko funko);
    void deleteById(Long id);
    void deleteAll();





}
