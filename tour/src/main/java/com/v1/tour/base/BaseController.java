package com.v1.tour.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.v1.tour.auth.userdetails.UserDetailsImpl;
import com.v1.tour.exception.CustomException;
import com.v1.tour.role.RoleModel;
import com.v1.tour.user.dao.UserInfoResponse;
import com.v1.tour.utils.Constants.ErrorType;

public class BaseController {
    Repositories repositories;

    @Autowired
    public void injectDependencies(Repositories repositories) {
        this.repositories = repositories;
    }

    public ResponseEntity<ResponseObject> onSuccess(Object data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseObject.builder().data(data).build());
    }

    public UserDetailsImpl getUserDetailsImpl() {
        return (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    public UserInfoResponse getUserInfo() {
        var userId = this.getUserDetailsImpl().getId();
        var user = this.repositories.userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorType.ID_NOT_FOUND));

        var roles = this.repositories.roleRepository.findAllByUserId(userId);
        var roleNames = roles.stream().map(RoleModel::getName).toList();
        return UserInfoResponse.builder().user(user).roles(roleNames).build();
    }
}
