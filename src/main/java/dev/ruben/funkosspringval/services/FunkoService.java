package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.models.Funko;

import java.util.List;
import java.util.Optional;

public interface FunkoService {
    public List<Funko> getAll();
    public Optional<Funko> getFunkoById(Long id);
    public void putFunko(Funko funko);
    public void deleteFunkoById(Long id);


}
