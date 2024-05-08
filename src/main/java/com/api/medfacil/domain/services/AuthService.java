package com.api.medfacil.domain.services;

import com.api.medfacil.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    public String generateRandomCode(){
        Random random = new Random();
        return String.valueOf(random.nextInt(9000) + 1000);
    }

    public User getUserAuthentication(){
        SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        String cpf = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userService.findByCpf(cpf);
        return user;
    }
}
