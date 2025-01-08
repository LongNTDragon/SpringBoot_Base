package com.v1.leadservice.auth.rolesallowed;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.v1.leadservice.enums.EnumRoleName;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RolesAllowed {
    EnumRoleName[] value();
}
