package com.v1.leadservice.base;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.v1.leadservice.exception.CustomException;
import com.v1.leadservice.utils.Constants.ErrorType;

public class BaseService<M extends BaseModel, R extends BaseRepository<M>> {
    protected Repositories repositories;
    protected R repository;
    protected ModelMapper mapper;

    public BaseService(R repository) {
        this.repository = repository;
    }

    @Autowired
    public void injectDependencies(Repositories repositories, ModelMapper mapper) {
        this.repositories = repositories;
        this.mapper = mapper;
    }

    public M findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ID_NOT_FOUND));
    }

    public M create(BaseDto baseDto, Class<M> classType) {
        var baseModel = mapper.map(baseDto, classType);
        return repository.save(baseModel);
    }

    public M updateById(String id, BaseDto baseDto) {
        var model = repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ID_NOT_FOUND));
        model.updateByDto(baseDto);
        return repository.save(model);
    }

    public M deleteById(String id) {
        var model = repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ID_NOT_FOUND));
        repository.deleteById(id);
        return model;
    }
}
