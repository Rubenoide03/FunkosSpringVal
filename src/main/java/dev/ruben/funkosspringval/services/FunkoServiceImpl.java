package dev.ruben.funkosspringval.services;

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


    @Autowired
    public FunkoServiceImpl(FunkosRepositoryImpl funkosRepository) {
        this.funkosRepository = funkosRepository;
    }

    @Override
    @Cacheable
    public List<Funko> getAll() {
        log.info("Getting all funkos");
        return funkosRepository.getAll();


    }

    @Override
    @Cacheable
    public Optional<Funko> getFunkoById(Long id) {
        log.info("Getting funko by id");
        return funkosRepository.getById(id);


    }
    @Override
    @Cacheable
    public Optional<Funko> getFunkoByName(String name) {
        log.info("Getting funko by name");
        return funkosRepository.getByName(name);
    }


    @Override
    @CachePut
    public Optional<Funko> putFunko(Funko funko) {
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
