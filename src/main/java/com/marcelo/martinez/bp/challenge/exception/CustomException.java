package com.marcelo.martinez.bp.challenge.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

   private final ExceptionsCode exceptionsCode;

   public CustomException(String message, ExceptionsCode exceptionCode) {
      super(message);
      this.exceptionsCode = exceptionCode;
   }

   public CustomException(String message, ExceptionsCode exceptionCode, Throwable cause) {
      super(message, cause);
      this.exceptionsCode = exceptionCode;
   }

   @Override
   public String toString() {
      return exceptionsCode + " :: " + super.toString();
   }

}
