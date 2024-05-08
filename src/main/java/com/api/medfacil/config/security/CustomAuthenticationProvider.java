package com.api.medfacil.config.security;

import com.api.medfacil.domain.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String cpf = authentication.getName();
        String code = authentication.getCredentials().toString();

        UserDetails user = userService.loadUserByUsername(cpf);

        if(isNull(user.getPassword()))
            throw new BadCredentialsException("Solicite seu código de acesso e faça seu login");

        if(!passwordEncoder.matches(code, user.getPassword()))
            throw new AuthenticationException("Usuário/Código inválidos") {};

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

