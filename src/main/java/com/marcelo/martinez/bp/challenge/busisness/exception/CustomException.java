package com.marcelo.martinez.bp.challenge.busisness.exception;

import java.util.List;

import com.marcelo.martinez.bp.challenge.api.ExceptionsCode;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

   private final ExceptionsCode exceptionsCode;

   private List<String> fields;

   public CustomException(ExceptionsCode exceptionCode) {
      this.exceptionsCode = exceptionCode;
   }

   public CustomException(String message, ExceptionsCode exceptionCode) {
      super(message);
      this.exceptionsCode = exceptionCode;
   }

   public CustomException(String message, ExceptionsCode exceptionCode, List<String> fields) {
      super(message);
      this.exceptionsCode = exceptionCode;
      this.fields = fields;
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
