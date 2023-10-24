package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.models.Funko;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FunkoService {
    public List<Funko> getAll();
    public Optional<Funko> getFunkoById(UUID id);
    public Optional<Funko> postFunko(Funko funko);
    public void deleteFunkoById(UUID id);
    public void deleteAll();
    public Optional<Funko> getFunkoByName(String name);
    public void update(UUID id,Funko funko);


}
