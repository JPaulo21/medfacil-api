package com.api.medfacil.web.controller;

import com.api.medfacil.config.security.JWT.TokenDTO;
import com.api.medfacil.config.security.JWT.TokenService;
import com.api.medfacil.domain.entities.Message;
import com.api.medfacil.domain.entities.User;
import com.api.medfacil.domain.services.AuthService;
import com.api.medfacil.domain.services.MessagesService;
import com.api.medfacil.domain.services.UserService;
import com.api.medfacil.web.docs.AuthDocs;
import com.api.medfacil.web.dto.login.CpfDTO;
import com.api.medfacil.web.dto.login.LoginDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthDocs {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AuthService authService;
    private final MessagesService messagesService;
    private final TokenService tokenService;

    @PostMapping("/generate-code")
    public ResponseEntity<CodeDTO> preLogin(@RequestBody CpfDTO cpfDTO){
        String code = authService.generateRandomCode();
        userService.updatePassword(cpfDTO.cpf(), code);
        return ResponseEntity.ok(new CodeDTO(code));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginDTO loginDTO)  {
        var authToken = new UsernamePasswordAuthenticationToken(loginDTO.cpf(), loginDTO.code());
        Authentication auth;
        try{
            auth = authenticationManager.authenticate(authToken);
        } catch (AuthenticationException e){
            log.error(e.getMessage());
            throw new AuthenticationException(e.getMessage()) {};
        }

        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TokenDTO token = new TokenDTO(tokenService.generateToken(user));
        userService.cleanPassword(user.getCpf());
        return ResponseEntity.ok(token);
    }
}
