package com.v1.tour.user;

import org.springframework.stereotype.Service;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;
import com.v1.tour.exception.CustomException;
import com.v1.tour.utils.Constants.ErrorType;

@Service
public class UserService extends BaseService<UserModel, UserRepository> {
    public UserService(Repositories repositories) {
        super(repositories.userRepository);
    }

    public Boolean existsByEmail(String email) {
        return super.repository.existsByEmail(email);
    }

    public UserModel findByEmail(String email) {
        return super.repository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorType.EMAIL_NOT_FOUND));
    }
}
