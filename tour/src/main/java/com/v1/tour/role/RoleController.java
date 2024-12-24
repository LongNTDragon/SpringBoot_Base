package com.v1.tour.role;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.tour.auth.rolesallowed.RolesAllowed;
import com.v1.tour.base.BaseController;
import com.v1.tour.base.ResponseObject;
import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.role.dto.RoleDto;
import com.v1.tour.utils.Constants.UrlPath;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = UrlPath.ROLE)
@RequiredArgsConstructor
public class RoleController extends BaseController {
    private final RoleService service;

    @GetMapping("")
    @RolesAllowed({ EnumRoleName.ADMIN })
    public ResponseEntity<ResponseObject> getAll() {
        return this.onSuccess(service.findAll());
    }

    @PostMapping("")
    @RolesAllowed({ EnumRoleName.ADMIN })
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody RoleDto roleDto) {
        return this.onSuccess(service.create(roleDto));
    }

    @PatchMapping("{id}")
    @RolesAllowed({ EnumRoleName.ADMIN })
    public ResponseEntity<ResponseObject> update(@PathVariable UUID id, @RequestBody RoleDto roleDto) {
        return this.onSuccess(service.update(id, roleDto));
    }
}
