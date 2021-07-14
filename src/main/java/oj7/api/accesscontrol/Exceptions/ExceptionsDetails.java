package oj7.api.accesscontrol.Exceptions;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExceptionsDetails {
  protected String title;
  protected int status;
  protected String details;
  protected String developerMessage;
  protected LocalDateTime timestamp;
}
