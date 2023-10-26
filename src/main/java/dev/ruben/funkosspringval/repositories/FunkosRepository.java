package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FunkosRepository {

     List<FunkoDTOResponse> getAll();
     FunkoDTOResponse getById(Long id);
     void put(FunkoDTOResponse funko);
     void deleteById(Long id);
     void deleteAll();
     void update(Long id, FunkoDTOResponse funko);





}
