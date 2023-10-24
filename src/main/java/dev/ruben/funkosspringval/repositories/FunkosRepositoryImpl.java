package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.models.Funko;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FunkosRepositoryImpl implements FunkosRepository {
    private final List<Funko> funkos = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Funko> getAll() {
        return funkos;
    }

    @Override
    public Optional<Funko> getById(UUID id) {
        return funkos.stream()
                .filter(funko -> funko.getId()==(id))
        .findFirst();}

    @Override
    public void put(Funko funko) {
        funko.setId(UUID.randomUUID());
        funkos.add(funko);
    }
    @Override
    public Optional<Funko> getByName(String name) {
        return funkos.stream()
                .filter(funko -> funko.getName().equals(name)).findFirst()
                ;
    }

    @Override
    public void deleteById(UUID id) {
        funkos.removeIf(funko -> funko.getId()==(id));
    }

    @Override
    public void deleteAll() {
        funkos.clear();
    }


    public void update(UUID id, Funko funko) {
        Optional<Funko> existingFunko = getById(id);
        existingFunko.ifPresent(f -> {
            f.setId(funko.getId());
            f.setName(funko.getName());
            f.setPrice(funko.getPrice());
            f.setStock(funko.getStock());
            f.setImage(funko.getImage());
            f.setModel(funko.getModel());
            f.setCreatedAt(funko.getCreatedAt());
            f.setUpdatedAt(funko.getUpdatedAt());

        });
    }


    }


