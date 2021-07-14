package oj7.api.accesscontrol.handler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import oj7.api.accesscontrol.Exceptions.BadRequestException;
import oj7.api.accesscontrol.Exceptions.BadRequestExceptionDetails;
import oj7.api.accesscontrol.Exceptions.NoSuchElementExceptionDetails;

@ControllerAdvice
public class RestExceptionHandler {
  
  @ExceptionHandler(NoSuchElementException.class)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<NoSuchElementExceptionDetails> noSuchElementExceptionHandler(NoSuchElementException e) {
    return new ResponseEntity<>(
        NoSuchElementExceptionDetails.builder()
          .timestamp(LocalDateTime.now())
          .status(HttpStatus.NO_CONTENT.value())
          .title("No content to request")
          .details(e.getMessage())
          .developerMessage(e.getClass().getName())
          .build(), HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<BadRequestExceptionDetails> badRequestExceptionHandler(NoSuchElementException e) {
    return new ResponseEntity<>(
        BadRequestExceptionDetails.builder()
          .timestamp(LocalDateTime.now())
          .status(HttpStatus.BAD_REQUEST.value())
          .title(">>Bad request")
          .details(e.getMessage())
          .developerMessage(e.getClass().getName())
          .build(), HttpStatus.BAD_REQUEST);
  }
}
