package com.v1.tour.role;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.tour.base.BaseController;
import com.v1.tour.role.dto.RoleDto;
import com.v1.tour.utils.ResponseObject;
import com.v1.tour.utils.Constants.UrlPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = UrlPath.ROLE)
@RequiredArgsConstructor
public class RoleController extends BaseController {
    private final RoleService service;

    @PostMapping("")
    public ResponseEntity<ResponseObject> create(@RequestBody RoleDto roleDto) {
        return this.onSuccess(service.create(roleDto));
    }
}
