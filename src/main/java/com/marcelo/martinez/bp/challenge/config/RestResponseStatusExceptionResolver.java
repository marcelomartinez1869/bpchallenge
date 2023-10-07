package com.marcelo.martinez.bp.challenge.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.marcelo.martinez.bp.challenge.dto.ErrorResponseDto;
import com.marcelo.martinez.bp.challenge.exception.CustomException;

@RestControllerAdvice
class RestResponseStatusExceptionResolver {

   @ExceptionHandler({ CustomException.class })
   public ResponseEntity<ErrorResponseDto> resolveException(CustomException exception) {
      return new ResponseEntity<>(new ErrorResponseDto(exception.getExceptionsCode().getError()), exception.getExceptionsCode().getHttpStatus());
   }

}
