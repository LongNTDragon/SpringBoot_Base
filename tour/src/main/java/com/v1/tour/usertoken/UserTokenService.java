package com.v1.tour.usertoken;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.v1.tour.base.BaseService;
import com.v1.tour.base.Repositories;
import com.v1.tour.exception.CustomException;
import com.v1.tour.usertoken.dto.UserTokenDto;
import com.v1.tour.utils.Constants.ErrorType;

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

    public UserTokenModel findByRefreshToken(UUID refreshToken) {
        return repository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new CustomException(ErrorType.REFRESH_TOKEN_NOT_FOUND));
    }

    public UserTokenModel updateIsRefreshed(UUID id, Boolean isRefreshed) {
        var userToken = super.findById(id);
        userToken.setIsRefreshed(isRefreshed);
        return super.repository.save(userToken);
    }
}
