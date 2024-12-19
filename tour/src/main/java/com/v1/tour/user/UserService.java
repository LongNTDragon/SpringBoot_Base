package com.v1.tour.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;

@Service
public class UserService extends BaseService<UserModel, UserRepository> {
    public UserService(Repositories repositories) {
        super(repositories.userRepository);
    }

    public Boolean existsByEmail(String email) {
        return super.repository.existsByEmail(email);
    }

    public Optional<UserModel> findByEmail(String email) {
        return super.repository.findByEmail(email);
    }
}
