package com.v1.tour.base;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<M extends BaseModel> extends JpaRepository<M, UUID> {
}
