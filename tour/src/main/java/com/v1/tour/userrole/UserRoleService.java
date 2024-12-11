package com.v1.tour.userrole;

import org.springframework.stereotype.Service;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;

@Service
public class UserRoleService extends BaseService<UserRoleModel, UserRoleRepository> {
    public UserRoleService(Repositories repositories) {
        super(repositories.userRoleRepository);
    }
}
