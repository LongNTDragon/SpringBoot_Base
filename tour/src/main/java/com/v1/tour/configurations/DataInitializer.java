package com.v1.tour.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.role.RoleService;
import com.v1.tour.user.UserModel;
import com.v1.tour.user.UserService;
import com.v1.tour.user.dto.UserDto;
import com.v1.tour.userrole.UserRoleModel;
import com.v1.tour.userrole.UserRoleService;
import com.v1.tour.userrole.dto.UserRoleDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private final RoleService roleService;
    private final UserService userService;
    private final UserRoleService userRoleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var role = roleService.findOrCreateNewByName(EnumRoleName.ADMIN);
        var email = "admin@gmail.com";
        var isExistEmail = userService.existsByEmail(email);
        if (Boolean.FALSE.equals(isExistEmail)) {
            var userDto = UserDto.builder()
                    .email(email)
                    .username("admin")
                    .password("123456")
                    .build();
            var user = userService.create(userDto, UserModel.class);

            var userRoleDto = UserRoleDto.builder()
                    .userId(user.getId())
                    .roleId(role.getId())
                    .build();
            userRoleService.create(userRoleDto, UserRoleModel.class);
        }
        logger.info("Data initialized");
    }
}
