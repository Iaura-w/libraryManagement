package org.project.librarymanagement.customer.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class CustomerControllerErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseBody
    public CustomerErrorResponse customerNotFound(CustomerNotFoundException exception) {
        final String message = exception.getMessage();
        log.error(message);
        return new CustomerErrorResponse(message, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CustomerCannotBeDeletedException.class)
    @ResponseBody
    public CustomerErrorResponse customerCannotBeDeleted(CustomerCannotBeDeletedException exception) {
        final String message = exception.getMessage();
        log.error(message);
        return new CustomerErrorResponse(message, HttpStatus.CONFLICT);
    }
}
