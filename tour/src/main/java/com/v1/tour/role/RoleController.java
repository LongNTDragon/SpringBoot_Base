package com.v1.tour.role;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.tour.base.BaseController;
import com.v1.tour.base.ResponseObject;
import com.v1.tour.role.dto.RoleDto;
import com.v1.tour.utils.Constants.UrlPath;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = UrlPath.ROLE)
@RequiredArgsConstructor
public class RoleController extends BaseController {
    private final RoleService service;

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable UUID id) {
        return this.onSuccess(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> create(@RequestBody RoleDto roleDto) {
        return this.onSuccess(service.create(roleDto));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable UUID id, @RequestBody RoleDto roleDto) {
        return this.onSuccess(service.updateById(id, roleDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable UUID id) {
        return this.onSuccess(service.deleteById(id));
    }
}
