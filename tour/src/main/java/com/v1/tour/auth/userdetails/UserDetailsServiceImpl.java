package com.v1.tour.auth.userdetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.v1.tour.base.Repositories;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Repositories repositories;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repositories.userRepository.findByEmail(username);

        var roles = repositories.roleRepository.findAllByUserId(user.get().getId());

        return UserDetailsImpl.build(user.get(), roles);
    }

}
