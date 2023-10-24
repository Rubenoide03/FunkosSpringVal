package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.models.Funko;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FunkosRepository {

     List<Funko> getAll();
     Optional<Funko> getById(Long id);
     void put(Funko funko);
     void deleteById(Long id);
     void deleteAll();
     Optional<Funko> getByName(String name);
     void update(Long id,Funko funko);





}
