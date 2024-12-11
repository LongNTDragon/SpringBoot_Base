package com.v1.tour.role;

import java.util.Optional;

import com.v1.tour.base.BaseRepository;
import com.v1.tour.enums.EnumRoleName;

public interface RoleRepository extends BaseRepository<RoleModel> {
    Optional<RoleModel> findByName(EnumRoleName name);
}
