package org.project.librarymanagement.book.error;

import lombok.extern.log4j.Log4j2;
import org.project.librarymanagement.book.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class BookControllerErrorHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseBody
    public BookErrorResponse bookNotFound(BookNotFoundException exception) {
        final String message = exception.getMessage();
        log.error(message);
        return new BookErrorResponse(message, HttpStatus.NOT_FOUND);
    }
}
