package com.v1.tour.user;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.v1.tour.auth.rolesallowed.RolesAllowed;
import com.v1.tour.base.BaseController;
import com.v1.tour.base.ResponseObject;
import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.user.dto.UserDto;
import com.v1.tour.user.dto.UserInfoUpdateDto;
import com.v1.tour.utils.Constants.ParamDefault;
import com.v1.tour.utils.Constants.UrlPath;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = UrlPath.USER)
@RequiredArgsConstructor
public class UserController extends BaseController {
    private final UserService service;

    @GetMapping("")
    @RolesAllowed({ EnumRoleName.ADMIN })
    public ResponseEntity<ResponseObject> getAll(@RequestParam(defaultValue = ParamDefault.PAGE) Integer page) {
        return this.onSuccess(service.findAll(page));
    }

    @PostMapping("")
    @RolesAllowed({ EnumRoleName.ADMIN })
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody UserDto userDto) {
        return this.onSuccess(service.create(userDto));
    }

    @PatchMapping("{id}")
    @RolesAllowed({ EnumRoleName.ADMIN })
    public ResponseEntity<ResponseObject> update(@PathVariable UUID id, @RequestBody UserDto userDto) {
        return this.onSuccess(service.update(id, userDto));
    }

    @GetMapping("info")
    public ResponseEntity<ResponseObject> getInfo() {
        var user = super.getUserInfo();
        return this.onSuccess(user);
    }

    @PatchMapping("info")
    public ResponseEntity<ResponseObject> updateInfo(@RequestBody UserInfoUpdateDto userInfoUpdateDto) {
        var user = super.getUserDetailsImpl();
        return this.onSuccess(service.updateInfo(user.getId(), userInfoUpdateDto));
    }
}
