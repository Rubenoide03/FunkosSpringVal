package dev.ruben.funkosspringval.services;

import dev.ruben.funkosspringval.dto.FunkoDTO;
import dev.ruben.funkosspringval.exceptions.FunkoNotFoundException;
import dev.ruben.funkosspringval.mappers.FunkoMapper;
import dev.ruben.funkosspringval.models.Funko;
import dev.ruben.funkosspringval.repositories.FunkosRepositoryImpl;
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
    public List<FunkoDTO> getAll() {
        log.info("Getting all funkos");
        return funkosRepository.getAll().stream().map(funkoMapper::toFunkoDTO).toList();


    }

    @Override
    @Cacheable
    public Optional<FunkoDTO> getFunkoById(Long id) {
        log.info("Getting funko by id");
        return funkosRepository.getById((id)).map(funkoMapper::toFunkoDTO);



    }
    @Override
    @Cacheable
    public Optional<FunkoDTO> getFunkoByName(String name) {
        log.info("Getting funko by name");
        return funkosRepository.getByName(name).map(funkoMapper::toFunkoDTO);
    }


    @Override
    @CachePut
    public Optional<Funko> postFunko(Funko funko) {
        log.info("Putting funko");
        funkosRepository.put(funko);
        return Optional.of(funko);

    }

    @Override
    @CacheEvict
    public void deleteFunkoById(Long id) {
        log.info("Deleting funko by id");
        funkosRepository.deleteById(id);



    }

    @Cacheable
    public Funko getFunkosByName(String name) {
        log.info("Getting funkos by model");
        return funkosRepository.getByName(name).orElseThrow(() -> new FunkoNotFoundException("Funko con nombre " + name + " no encontrado"));
    }

    @Override
    @CacheEvict
    public void deleteAll() {
        log.info("Deleting all funkos");
        funkosRepository.deleteAll();


    }

    @Override
    @CachePut
    public void update(Long id ,Funko funko) {
        log.info("Updating funko");
        funkosRepository.update(id,funko);


    }

}
