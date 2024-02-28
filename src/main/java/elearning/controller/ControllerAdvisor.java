package elearning.controller;

import elearning.dto.response.ResponseError;
import elearning.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ResponseError> handleLoginExcept(LoginException ex, HttpServletRequest request){
        return new ResponseEntity<>(new ResponseError(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),new Date(),request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }
}
