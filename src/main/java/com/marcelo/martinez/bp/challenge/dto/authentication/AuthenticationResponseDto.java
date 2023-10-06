package com.marcelo.martinez.bp.challenge.dto.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponseDto {

   @JsonProperty("accessToken")
   private String token;

   private int expiration;

}
