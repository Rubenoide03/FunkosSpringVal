package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.models.Funko;

import java.util.List;
import java.util.Optional;

public interface FunkoService {
    public List<Funko> getAll();
    public Optional<Funko> getFunkoById(Long id);
    public Optional<Funko> postFunko(Funko funko);
    public void deleteFunkoById(Long id);
    public void deleteAll();
    public Optional<Funko> getFunkoByName(String name);
    public void update(Long id,Funko funko);


}
