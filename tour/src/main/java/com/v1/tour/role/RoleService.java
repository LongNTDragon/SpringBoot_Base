package com.v1.tour.role;

import org.springframework.stereotype.Service;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;
import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.role.dto.RoleDto;

@Service
public class RoleService extends BaseService<RoleModel, RoleRepository> {
    public RoleService(Repositories repositories) {
        super(repositories.roleRepository);
    }

    public RoleModel create(RoleDto roleDto) {
        return super.create(roleDto, RoleModel.class);
    }

    public RoleModel findOrCreateNewByName(EnumRoleName name) {
        var role = super.repository.findByName(name);
        if (role.isPresent())
            return role.get();

        var roleDto = RoleDto.builder().name(name).build();
        return this.create(roleDto);
    }
}
