package com.v1.tour.role;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;
import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.exception.CustomException;
import com.v1.tour.role.dto.RoleDto;
import com.v1.tour.utils.Constants.ErrorType;

@Service
public class RoleService extends BaseService<RoleModel, RoleRepository> {
    public RoleService(Repositories repositories) {
        super(repositories.roleRepository);
    }

    public List<RoleModel> findAll() {
        return super.repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public RoleModel create(RoleDto roleDto) {
        validateRoleName(roleDto, null);
        return super.create(roleDto, RoleModel.class);
    }

    public RoleModel update(UUID id, RoleDto roleDto) {
        validateRoleName(roleDto, id);
        return super.updateById(id, roleDto);
    }

    private void validateRoleName(RoleDto roleDto, UUID id) {
        var role = super.repository.findByName(roleDto.getName());
        if (role.isPresent() && id == null)
            throw new CustomException(ErrorType.ROLE_NAME_EXISTS);

        if (role.isPresent() && !role.get().getId().equals(id))
            throw new CustomException(ErrorType.ROLE_NAME_EXISTS);
    }

    public RoleModel findOrCreateNewByName(EnumRoleName name) {
        var role = super.repository.findByName(name);
        if (role.isPresent())
            return role.get();

        var roleDto = RoleDto.builder().name(name).build();
        return this.create(roleDto);
    }

    public List<RoleModel> findAllByUserId(UUID userId) {
        return super.repository.findAllByUserId(userId);
    }
}
