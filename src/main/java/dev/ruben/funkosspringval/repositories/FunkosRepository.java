package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.models.Funko;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FunkosRepository {

     List<FunkoDTOResponse> getAll();
     FunkoDTOResponse getById(Long id);
     void put(FunkoDTOResponse funko);
     void deleteById(Long id);
     void deleteAll();
     FunkoDTOResponse getByName(String name);
     void update(Long id, FunkoDTOResponse funko);





}
