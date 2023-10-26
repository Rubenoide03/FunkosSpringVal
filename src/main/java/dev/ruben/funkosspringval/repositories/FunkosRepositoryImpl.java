package dev.ruben.funkosspringval.repositories;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.exceptions.FunkoNotAvailableAddException;
import dev.ruben.funkosspringval.exceptions.FunkoNotFoundException;
import dev.ruben.funkosspringval.models.Funko;
import org.springframework.stereotype.Repository;
//funkoMapper
import dev.ruben.funkosspringval.mappers.FunkoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FunkosRepositoryImpl implements FunkosRepository {
    private final List<Funko> funkos = new ArrayList<>();
    FunkoMapper funkoMapper = new FunkoMapper();
    private Long nextId = 1L;

    @Override
    public List<FunkoDTOResponse> getAll() {
        return funkoMapper.toDTO(funkos);
    }

    @Override
    public FunkoDTOResponse getById(Long id) {
        Funko funko = (funkos.stream()
                .filter(funko1 -> funko1.getId() == (id))
                .findFirst()).orElseThrow(() -> new FunkoNotFoundException("Funko con id " + id + " no ha sido encontrado"));
        return funkoMapper.toDTO(funko);
    }

    @Override
    public void put(FunkoDTOResponse funko) {
        try {
            funko.setId(nextId++);
            funkos.add(funkoMapper.toFunko(funko));

        } catch (FunkoNotAvailableAddException e) {
            throw new FunkoNotAvailableAddException("Funko con el siguiente id " + nextId + " no encontrado y no se puedo actualizar");
        }

    }


    @Override
    public void deleteById(Long id) {
        try {
            funkos.removeIf(funko -> funko.getId().equals(id));

        } catch (FunkoNotFoundException e) {
            throw new FunkoNotFoundException("Funko con id " + id + " no encontrado");

        }
    }

        @Override
        public void deleteAll () {
            funkos.clear();
        }



    public void update(Long id, FunkoDTOResponse funko) {
        Optional<Funko> existingFunko = funkos.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
        existingFunko.ifPresent(f -> {
            f.setId(funko.getId());
            f.setName(funko.getName());
            f.setPrice(funko.getPrice());
            f.setStock(funko.getStock());
            f.setImage(funko.getImage());
            f.setModel(funko.getModel());


        });
    }
}





