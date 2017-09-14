package com.incamp.mhs.security;

import com.incamp.mhs.role.Role;
import com.incamp.mhs.user.User;
import com.incamp.mhs.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UsernamePasswordAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User userByUsername;
        try {
            userByUsername = userService.getUserByUsername(authentication.getPrincipal().toString());
        } catch (EntityNotFoundException e) {
            throw new BadCredentialsException("auth error");
        }

        if (Objects.equals(authentication.getPrincipal(), userByUsername.getUsername()) && bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), userByUsername.getPassword())) {
            Set<String> roles = userByUsername.getRoles().stream().map(Role::getAuthority).collect(Collectors.toSet());
            String token = JwtAuthHelper.createJwt(userByUsername.getId(), roles);
            return new SsnJwtAuthentication(token);
        }
        throw new BadCredentialsException("auth error");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
