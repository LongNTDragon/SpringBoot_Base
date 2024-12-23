package com.v1.tour.usertoken;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;
import com.v1.tour.usertoken.dto.UserTokenDto;

@Service
public class UserTokenService extends BaseService<UserTokenModel, UserTokenRepository> {
    @Value("${spring.jwt.expiration-time.refresh-token}")
    private Long refreshTokenExpiration;

    public UserTokenService(Repositories repositories) {
        super(repositories.userTokenRepository);
    }

    public UserTokenModel create(UserTokenDto userTokenDto) {
        userTokenDto.setRefreshTokenExpire(
                new Date(new Date().getTime() + this.refreshTokenExpiration));
        return super.create(userTokenDto, UserTokenModel.class);
    }
}
