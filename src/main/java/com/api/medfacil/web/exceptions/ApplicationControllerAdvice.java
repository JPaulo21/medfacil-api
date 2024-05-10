package com.api.medfacil.web.exceptions;

import com.api.medfacil.domain.exceptions.CpfRegisteredException;
import com.api.medfacil.domain.exceptions.FullNumberRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseError> authenticationException(AuthenticationException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseError(HttpStatus.UNAUTHORIZED, 401, e.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseError> BadCredentialsException(BadCredentialsException e){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseError(HttpStatus.UNAUTHORIZED, 401, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> MethodArgumentNotValidException(MethodArgumentNotValidException e, BindingResult result) {
        log.info("{}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseError(HttpStatus.BAD_REQUEST, 400, result.getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseError> usernameNotFoundException(UsernameNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseError(HttpStatus.NOT_FOUND, 404, e.getMessage()));
    }

    @ExceptionHandler(CpfRegisteredException.class)
    public ResponseEntity<ResponseError> cpfRegisteredException(CpfRegisteredException e){
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseError(HttpStatus.BAD_REQUEST, 400, e.getMessage()));
    }

    @ExceptionHandler(FullNumberRegisteredException.class)
    public ResponseEntity<ResponseError> fullNumberRegisteredException(FullNumberRegisteredException e){
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseError(HttpStatus.BAD_REQUEST, 400, e.getMessage()));
    }


}
