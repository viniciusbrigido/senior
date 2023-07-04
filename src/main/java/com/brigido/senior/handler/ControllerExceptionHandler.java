package com.brigido.senior.handler;

import com.brigido.senior.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
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

    @ExceptionHandler({
            NotFoundEntityException.class,
            InvalidCpfException.class,
            ScheduleDateExpiredException.class,
            AssociateHasAlreadyVotedException.class
    })
    public ResponseEntity<Object> handleNotFoundEntity(RuntimeException runtimeException) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(getErrorResponse(runtimeException.getMessage(), status), status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleForeignKeyViolation(DataIntegrityViolationException dataIntegrityViolationException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "The table cannot be deleted because it has references in another table.";
        return new ResponseEntity<>(getErrorResponse(message, status), status);
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
