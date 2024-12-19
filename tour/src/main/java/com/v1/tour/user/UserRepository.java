package com.v1.tour.user;

import java.util.Optional;

import com.v1.tour.base.BaseRepository;

public interface UserRepository extends BaseRepository<UserModel> {
    Boolean existsByEmail(String email);

    Optional<UserModel> findByEmail(String email);
}