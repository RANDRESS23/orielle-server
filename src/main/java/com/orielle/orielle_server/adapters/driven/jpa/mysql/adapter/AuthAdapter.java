package com.orielle.orielle_server.adapters.driven.jpa.mysql.adapter;

import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.entity.UserEntity;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.orielle.orielle_server.adapters.driven.jpa.mysql.util.DrivenConstants;
import com.orielle.orielle_server.configuration.security_config.jwt_configuration.JwtService;
import com.orielle.orielle_server.domain.model.User;
import com.orielle.orielle_server.domain.spi.IAuthPersistencePort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class AuthAdapter implements IAuthPersistencePort {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IRoleEntityMapper roleEntityMapper;
    private final JwtService jwtService;

    @Override
    public Long getAuthenticatedUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader(DrivenConstants.AUTHORIZATION_HEADER).substring(7);

        return jwtService.extractUserId(token);
    }

    @Override
    public String getAuthenticatedUserEmail() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader(DrivenConstants.AUTHORIZATION_HEADER).substring(7);

        return jwtService.extractEmail(token);
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);

        if (userEntity == null) return false;

        return passwordEncoder.matches(password, userEntity.getPassword());
    }

    @Override
    public User authenticate(String email, String password) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                email,
                password
            )
        );

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow();

        return IUserEntityMapper.toDomainModel(userEntity, roleEntityMapper);
    }

    @Override
    public String generateToken(User user) {
        return jwtService.generateToken(generateExtraClaims(user), user);
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        RoleEntity roleEntity = roleRepository.findByName(user.getRole().getName()).orElseThrow();
        extraClaims.put(DrivenConstants.USER_ID_FIELD, user.getUserId());
        extraClaims.put(DrivenConstants.ROLE_FIELD, roleEntity.getName());
        extraClaims.put(DrivenConstants.FULL_NAME_FIELD, user.getName() + " " + user.getLastName());
        return extraClaims;
    }
}
