package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.dto.FunkoDTOResponse;
import dev.ruben.funkosspringval.exceptions.FunkoNotFoundException;
import dev.ruben.funkosspringval.mappers.FunkoMapper;
import dev.ruben.funkosspringval.models.Funko;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = "funkos")
public class FunkoServiceImpl implements FunkoService {

    private final FunkosRepositoryImpl funkosRepository;
    private final FunkoMapper funkoMapper;


    @Autowired
    public FunkoServiceImpl(FunkosRepositoryImpl funkosRepository, FunkoMapper funkoMapper) {
        this.funkosRepository = funkosRepository;
        this.funkoMapper = funkoMapper;
    }

    @Override
    @Cacheable
    public List<FunkoDTOResponse> getAll() {
        log.info("Getting all funkos");
        return funkosRepository.getAll();


    }

    @Override
    @Cacheable
    public FunkoDTOResponse getFunkoById(Long id) {
        log.info("Getting funko by id");
        try {
            FunkoDTOResponse funko = funkosRepository.getById(id);
            return funko;


        } catch (FunkoNotFoundException e) {
            throw new FunkoNotFoundException("Funko con id " + id + " no encontrado");


        }
    }



    @Override
    @CachePut
    public Optional<Funko> postFunko(FunkoDTOResponse funkoDTO) {
        log.info("Putting funko");

        funkosRepository.put(funkoDTO);
        return Optional.of(funkoMapper.toFunko(funkoDTO));

    }

    @Override
    @CacheEvict
    public void deleteFunkoById(Long id) {
        log.info("Deleting funko by id");
        funkosRepository.deleteById(id);


    }


    @Override
    @CacheEvict
    public void deleteAll() {
        log.info("Deleting all funkos");
        funkosRepository.deleteAll();


    }

    @Override
    @CachePut
    public Funko update(Long id, FunkoDTOResponse funko) {
        log.info("Updating funko");
        try {
            funkosRepository.update(id, funko);
            return funkoMapper.toFunko(funko);

        } catch (FunkoNotFoundException e) {
            throw new FunkoNotFoundException("Funko con id " + id + " no encontrado");
        }



    }

}
