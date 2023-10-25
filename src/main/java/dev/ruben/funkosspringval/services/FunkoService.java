package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.models.Funko;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FunkoService {
    public List<FunkoDTOResponse> getAll();

    public FunkoDTOResponse getFunkoById(Long id);
    public Optional<Funko> postFunko(FunkoDTOResponse funkoDTO);
    public void deleteFunkoById(Long id);
    public void deleteAll();
    public FunkoDTOResponse getFunkoByName(String name);
    public Funko update(Long id, FunkoDTOResponse funkoDTO);


}
