package com.brigido.senior.handler;

import com.brigido.senior.exception.*;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import static java.util.Objects.*;

@ControllerAdvice
@EnableWebMvc
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<Object> handleNotFoundEntity(NotFoundEntityException notFoundException) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(getErrorResponse(notFoundException.getMessage(), status), status);
    }

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<Object> handleInvalidCpf(InvalidCpfException invalidCpfException) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(getErrorResponse(invalidCpfException.getMessage(), status), status);
    }

    @ExceptionHandler(ScheduleDateExpiredException.class)
    public ResponseEntity<Object> handleScheduleDateExpired(ScheduleDateExpiredException scheduleDateExpiredException) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(getErrorResponse(scheduleDateExpiredException.getMessage(), status), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        if (nonNull(fieldError)) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            String errorMessage = "Field %s required.".formatted(fieldError.getField());
            return new ResponseEntity<>(getErrorResponse(errorMessage, status), status);
        }
        return super.handleMethodArgumentNotValid(ex, headers, statusCode, request);
    }

    private ErrorResponse getErrorResponse(String message, HttpStatus status) {
        String statusMessage = "%s - %s".formatted(status.value(), status.getReasonPhrase());
        return new ErrorResponse(statusMessage, message);
    }

}
