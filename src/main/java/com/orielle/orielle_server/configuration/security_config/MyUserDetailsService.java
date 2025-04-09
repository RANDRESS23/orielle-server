package com.orielle.orielle_server.configuration.security_config;

import com.orielle.orielle_server.configuration.security_config.jwt_configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String jwt) throws UsernameNotFoundException {
        String email = jwtService.extractEmail(jwt);
        String role = jwtService.extractRole(jwt);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role));

        return new User(email, "", authorityList);
    }
}
