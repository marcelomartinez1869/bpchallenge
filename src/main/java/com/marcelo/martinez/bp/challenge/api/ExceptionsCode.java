package com.marcelo.martinez.bp.challenge.api;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionsCode {

   INTERNAL_ERROR("generic_error", HttpStatus.INTERNAL_SERVER_ERROR),
   INVALID_GATEWAY_STATUS("invalid_gateway_status", HttpStatus.PRECONDITION_FAILED),
   INVALID_ORIGIN("invalid_origin", HttpStatus.PRECONDITION_FAILED),
   GATEWAY_TIMEOUT("gateway_time_out", HttpStatus.GATEWAY_TIMEOUT),
   INVALID_USER("invalid_user", HttpStatus.PRECONDITION_FAILED),
   INVALID_URL("invalid_url", HttpStatus.PRECONDITION_FAILED),
   INVALID_REQUEST("invalid_request", HttpStatus.PRECONDITION_FAILED),
   INVALID_CREDENTIAL("generic_error", HttpStatus.PRECONDITION_FAILED),
   CLIENT_ERROR("client_error", HttpStatus.FORBIDDEN),
   SUCCESS("success", HttpStatus.OK),
   NOT_FOUND("generic_error", HttpStatus.NOT_FOUND),
   BAD_REQUEST("generic_error", HttpStatus.BAD_REQUEST),
   FORBIDDEN("generic_error", HttpStatus.FORBIDDEN),
   INVALID("generic_error", HttpStatus.PRECONDITION_FAILED),
   INVALID_TOKEN("invalid_token", HttpStatus.PRECONDITION_FAILED),
   UNAUTHORIZED("unauthorized", HttpStatus.UNAUTHORIZED),
   UNKNOWN_ERROR("unknown_error", HttpStatus.PRECONDITION_FAILED),
   ANY_OTHER_REASON("any_other_reason", HttpStatus.PRECONDITION_FAILED);

   private final String error;

   private final HttpStatus httpStatus;

}
