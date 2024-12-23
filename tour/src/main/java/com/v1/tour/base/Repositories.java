package com.v1.tour.base;

import org.springframework.stereotype.Component;

import com.v1.tour.role.RoleRepository;
import com.v1.tour.user.UserRepository;
import com.v1.tour.userrole.UserRoleRepository;
import com.v1.tour.usertoken.UserTokenRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Repositories {
    public final RoleRepository roleRepository;
    public final UserRepository userRepository;
    public final UserRoleRepository userRoleRepository;
    public final UserTokenRepository userTokenRepository;
}
