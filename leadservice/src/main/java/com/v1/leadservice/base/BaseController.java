package com.v1.leadservice.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import com.v1.leadservice.auth.userdetails.UserDetailsImpl;

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
}
