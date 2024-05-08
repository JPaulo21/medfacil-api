package com.api.medfacil.config.security.JWT;

import com.api.medfacil.domain.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token == null){
            filterChain.doFilter(request, response);
            return;
        }

        var username = tokenService.validateToken(token);
        UserDetails user = userRepository.findByCpf(username).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),  null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) {
            log.debug("JWT Token está nulo, vazio ou não iniciado com 'Bearer '.");
            return null;
        }
        return authHeader.replace("Bearer ","");
    }
}
