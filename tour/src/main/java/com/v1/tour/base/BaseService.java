package com.v1.tour.base;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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

    public M create(BaseDto baseDto, Class<M> classType) {
        var baseModel = mapper.map(baseDto, classType);
        return repository.save(baseModel);
    }
}
