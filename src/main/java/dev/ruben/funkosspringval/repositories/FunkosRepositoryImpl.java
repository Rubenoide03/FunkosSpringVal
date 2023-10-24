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
    private long nextId = 1;

    @Override
    public List<Funko> getAll() {
        return funkos;
    }

    @Override
    public Optional<Funko> getById(Long id) {
        return funkos.stream()
                .filter(funko -> funko.getId().equals(id))
                .findFirst();
    }

    @Override
    public void put(Funko funko) {
        funko.setId(nextId++);
        funkos.add(funko);
    }

    @Override
    public Optional<Funko> getByName(String name) {
        return funkos.stream()
                .filter(funko -> funko.getName().equals(name)).findFirst()
                ;
    }

    public void deleteById(Long id) {
        funkos.removeIf(funko -> funko.getId() == (id));
    }

    @Override
    public void deleteAll() {
        funkos.clear();
    }


    public void update(Long id, Funko funko) {
        Optional<Funko> funkoOptional = getById(id);
        funkoOptional.ifPresent(f -> {
            f.setName(funko.getName());
            f.setPrice(funko.getPrice());
            f.setStock(funko.getStock());
            f.setImage(funko.getImage());
            f.setCategoria(funko.getCategoria());
            f.setCreatedAt(funko.getCreatedAt());
            f.setUpdatedAt(funko.getUpdatedAt());

        });
    }


}


