package com.v1.leadservice.base;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BaseRepository<M extends BaseModel> extends MongoRepository<M, String> {
}