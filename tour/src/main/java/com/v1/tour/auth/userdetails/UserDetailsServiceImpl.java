package com.v1.tour.auth.userdetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.v1.tour.role.RoleService;
import com.v1.tour.user.UserService;
import com.v1.tour.utils.Constants.ErrorType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorType.EMAIL_NOT_FOUND));

        var roles = roleService.findAllByUserId(user.getId());

        return UserDetailsImpl.build(user, roles);
    }

}
