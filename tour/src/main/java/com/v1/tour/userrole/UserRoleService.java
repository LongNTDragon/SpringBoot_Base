package com.v1.tour.userrole;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;
import com.v1.tour.userrole.dto.UserRoleDto;

@Service
public class UserRoleService extends BaseService<UserRoleModel, UserRoleRepository> {
    public UserRoleService(Repositories repositories) {
        super(repositories.userRoleRepository);
    }

    public List<UserRoleModel> createMulti(List<UserRoleDto> userRoleDtos) {
        var userRoleModels = userRoleDtos.stream().map(userRoleDto -> mapper.map(userRoleDto, UserRoleModel.class))
                .toList();
        return repository.saveAll(userRoleModels);
    }

    public void deleteByUserId(UUID userId) {
        super.repository.deleteByUserId(userId);
    }
}
