package oj7.api.accesscontrol.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import oj7.api.accesscontrol.Exceptions.BadRequestException;
import oj7.api.accesscontrol.Exceptions.BadRequestExceptionDetails;
import oj7.api.accesscontrol.Exceptions.NoContentException;
import oj7.api.accesscontrol.Exceptions.NoSuchElementExceptionDetails;

@ControllerAdvice
public class RestExceptionHandler {
  
  @ExceptionHandler(NoContentException.class)
  public ResponseEntity<NoSuchElementExceptionDetails> noSuchElementExceptionHandler(NoContentException e) {
    return new ResponseEntity<>(
        NoSuchElementExceptionDetails.builder()
          .timestamp(LocalDateTime.now())
          .status(HttpStatus.NOT_FOUND.value())
          .title("No content to request")
          .details(e.getMessage())
          .developerMessage(e.getClass().getName())
          .build(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<BadRequestExceptionDetails> badRequestExceptionHandler(BadRequestException e) {
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
