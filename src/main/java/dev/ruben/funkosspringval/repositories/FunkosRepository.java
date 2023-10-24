package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.models.Funko;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FunkosRepository {

     List<Funko> getAll();
     Optional<Funko> getById(UUID id);
     void put(Funko funko);
     void deleteById(UUID id);
     void deleteAll();
     Optional<Funko> getByName(String name);
     void update(UUID id,Funko funko);





}
