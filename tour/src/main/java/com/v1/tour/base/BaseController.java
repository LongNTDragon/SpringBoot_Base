package com.v1.tour.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.v1.tour.utils.ResponseObject;

public class BaseController {
    public ResponseEntity<ResponseObject> onSuccess(Object data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseObject.builder().success(true).data(data).build());
    }
}
