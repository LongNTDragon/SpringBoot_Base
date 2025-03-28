package com.v1.tour.base;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.v1.tour.exception.CustomException;
import com.v1.tour.utils.Constants.ErrorType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BaseService<M extends BaseModel, R extends BaseRepository<M>> {
    protected ModelMapper mapper;
    protected PasswordEncoder passwordEncoder;
    protected R repository;
    protected Repositories repositories;

    @PersistenceContext
    protected EntityManager entityManager;

    public BaseService(R repository) {
        this.repository = repository;
    }

    @Autowired
    public void injectDependencies(ModelMapper mapper, Repositories repositories,
            PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.repositories = repositories;
        this.passwordEncoder = passwordEncoder;
    }

    public M findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ID_NOT_FOUND));
    }

    public M create(BaseDto baseDto, Class<M> classType) {
        var baseModel = mapper.map(baseDto, classType);
        return repository.save(baseModel);
    }

    public M updateById(UUID id, BaseDto baseDto) {
        var model = repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ID_NOT_FOUND));
        model.updateByDto(baseDto);
        return repository.save(model);
    }

    public M deleteById(UUID id) {
        var model = repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ID_NOT_FOUND));
        repository.deleteById(id);
        return model;
    }
}
