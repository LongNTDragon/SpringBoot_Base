package com.v1.tour.role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.v1.tour.base.BaseRepository;
import com.v1.tour.enums.EnumRoleName;

public interface RoleRepository extends BaseRepository<RoleModel> {
    Optional<RoleModel> findByName(EnumRoleName name);

    @Query(value = "select r.* from role r inner join user_role ur on ur.role_id = r.id where ur.user_id = :userId", nativeQuery = true)
    List<RoleModel> findAllByUserId(@Param("userId") UUID userId);
}
