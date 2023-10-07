package com.marcelo.martinez.bp.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.martinez.bp.challenge.busisness.service.AuthenticationService;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationRequestDto;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationResponseDto;
import com.marcelo.martinez.bp.challenge.exception.CustomException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

   private AuthenticationService authenticationService;

   @PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
   @ResponseBody
   @Operation(summary = "Post", description = "Endpoint for API authentcation")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Token created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
         @ApiResponse(responseCode = "500", ref = "INTERNAL_ERROR"),
         @ApiResponse(responseCode = "504", ref = "GATEWAY_TIMEOUT"),
         @ApiResponse(responseCode = "501", ref = "UNAUTHORIZED"),
         @ApiResponse(responseCode = "401", ref = "UNAUTHORIZED"),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.") })
   public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authDto) throws CustomException {
      AuthenticationResponseDto dto = authenticationService.login(authDto);
      return ResponseEntity
            .status(HttpStatus.OK)
            .body(dto);
   }

}
