package com.v1.tour.base;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.v1.tour.exception.CustomException;
import com.v1.tour.utils.Constants.ErrorType;

public class BaseService<M extends BaseModel, R extends BaseRepository<M>> {
    ModelMapper mapper;
    R repository;
    Repositories repositories;

    public BaseService(R repository) {
        this.repository = repository;
    }

    @Autowired
    public void injectDependencies(ModelMapper mapper, Repositories repositories) {
        this.mapper = mapper;
        this.repositories = repositories;
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
