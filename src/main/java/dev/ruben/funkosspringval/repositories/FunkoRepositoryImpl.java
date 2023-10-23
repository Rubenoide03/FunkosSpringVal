package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.models.Funko;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class FunkoRepositoryImpl implements FunkosRepository{


    @Override
    public List<Funko> getAll() {

    }


    public Optional<Funko> getById(Long id) {

    }

    public void put(Funko funko) {

    }


    public void deleteById(Long id) {

    }
    public void deleteAll(){

    }
}
