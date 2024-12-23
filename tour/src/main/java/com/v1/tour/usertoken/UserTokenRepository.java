package com.v1.tour.usertoken;

import java.util.Optional;
import java.util.UUID;

import com.v1.tour.base.BaseRepository;

public interface UserTokenRepository extends BaseRepository<UserTokenModel> {
    Optional<UserTokenModel> findByRefreshToken(UUID refreshToken);
}
