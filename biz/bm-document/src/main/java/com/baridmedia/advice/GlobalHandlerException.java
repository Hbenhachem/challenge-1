package com.baridmedia.advice;

import com.baridmedia.dto.response.MessageErrorResponse;
import com.baridmedia.exception.DocumentNotFoundException;
import com.baridmedia.exception.DocumentSignException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.Set;


@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {


    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<MessageErrorResponse> handleDocumentNotFoundException(DocumentNotFoundException ex, WebRequest request) {
     log.error("catch UserNameExistException error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorResponse(
                Set.of(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getContextPath()
        ));
    }


    @ExceptionHandler(DocumentSignException.class)
    public ResponseEntity<MessageErrorResponse> handleDocumentSignException(DocumentSignException ex, WebRequest request) {
        log.error("catch NotFoundException error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageErrorResponse(
                Set.of(ex.getMessage()),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                request.getContextPath()
        ));
    }


}
