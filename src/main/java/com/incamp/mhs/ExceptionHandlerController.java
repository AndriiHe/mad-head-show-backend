package com.incamp.mhs;

import com.incamp.mhs.security.BadCredentialsException;
import com.incamp.mhs.user.UserAlreadyExistsException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionInfo entityNotFoundHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ExceptionInfo userAlreadyExistsHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ExceptionInfo badCredentialsHandler(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ExceptionInfo constraintViolation(HttpServletRequest request, PersistenceException e) {
        return new ExceptionInfo(request.getRequestURI(), e.getMessage());
    }
}
