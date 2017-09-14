package com.incamp.mhs.authentication;

import com.incamp.mhs.security.SsnJwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(LoginPasswordForm loginPasswordForm) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginPasswordForm.getLogin(), loginPasswordForm.getPassword()));
        return ((SsnJwtAuthentication) authenticate).getToken();
    }
}
