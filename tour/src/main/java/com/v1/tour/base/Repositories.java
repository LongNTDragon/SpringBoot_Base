package com.v1.tour.base;

import org.springframework.stereotype.Component;

import com.v1.tour.role.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Repositories {
    public final RoleRepository roleRepository;
}
