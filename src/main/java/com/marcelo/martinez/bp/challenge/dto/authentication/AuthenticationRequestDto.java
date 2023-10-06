package com.marcelo.martinez.bp.challenge.dto.authentication;

import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

   @ApiModelProperty(position = 1)
   private String user;

   @ApiModelProperty(position = 2 )
   private String password;

}
