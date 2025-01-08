package com.v1.leadservice.auth.rolesallowed;

import java.util.Arrays;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.v1.leadservice.utils.Constants.ErrorType;

@Aspect
@Component
public class RolesAllowedAspect {
    @Before("@annotation(rolesAllow)")
    public void checkRoles(RolesAllowed rolesAllow) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Boolean hasRole = Arrays.stream(rolesAllow.value())
                .anyMatch(role -> authentication.getAuthorities().contains(new SimpleGrantedAuthority(role.name())));

        if (Boolean.FALSE.equals(hasRole))
            throw new AccessDeniedException(ErrorType.ACCESS_DENIED);
    }
}
