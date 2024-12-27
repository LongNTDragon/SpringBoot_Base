package com.v1.tour.configurations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.role.RoleService;
import com.v1.tour.user.UserService;
import com.v1.tour.user.dto.UserDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private final RoleService roleService;
    private final UserService userService;

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
                    .roleIds(List.of(role.getId()))
                    .build();
            userService.create(userDto);
        }
        logger.info("Data initialized");
    }
}
