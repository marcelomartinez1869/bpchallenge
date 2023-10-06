package com.marcelo.martinez.bp.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.martinez.bp.challenge.busisness.service.AuthenticationService;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationRequestDto;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationResponseDto;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

   @Autowired
   private AuthenticationService authenticationService;

   @PostMapping(value = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
   @ResponseBody
   @Operation(summary = "Post", description = "Login")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Get the token", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
         @ApiResponse(responseCode = "500", ref = "INTERNAL_ERROR"), //
         @ApiResponse(responseCode = "504", ref = "GATEWAY_TIMEOUT"),
         @ApiResponse(responseCode = "401", ref = "UNAUTHORIZED"), //
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.") })
   public AuthenticationResponseDto login(@RequestBody AuthenticationRequestDto authDto) {
      return authenticationService.login(authDto);
   }

}
