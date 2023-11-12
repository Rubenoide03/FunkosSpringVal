package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface FunkosRepository extends JpaRepository<FunkoDTOResponse, Long> {

     List<FunkoDTOResponse> getAll();
     FunkoDTOResponse getById(Long id);
     void put(FunkoDTOResponse funko);
     void deleteById(Long id);
     void deleteAll();
     void update(Long id, FunkoDTOResponse funko);





}
