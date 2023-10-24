package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.dto.FunkoDTO;
import dev.ruben.funkosspringval.models.Funko;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FunkoService {
    List<FunkoDTO> getAll();
    Optional<FunkoDTO> getFunkoById(Long id);
    Optional<Funko> postFunko(Funko funko);
    void deleteFunkoById(Long id);
    void deleteAll();
    Optional<FunkoDTO> getFunkoByName(String name);
    public void update(Long id,Funko funko);


}
