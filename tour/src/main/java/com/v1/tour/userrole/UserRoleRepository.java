package com.v1.tour.userrole;

import java.util.UUID;

import com.v1.tour.base.BaseRepository;

public interface UserRoleRepository extends BaseRepository<UserRoleModel> {
    void deleteByUserId(UUID userId);
}
