package com.marcelo.martinez.bp.challenge.busisness.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationRequestDto;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationResponseDto;
import com.marcelo.martinez.bp.challenge.exception.CustomException;
import com.marcelo.martinez.bp.challenge.exception.ExceptionsCode;

@Service
public class AuthenticationService {

   private static final int EXPIRATION = 600000;

   @Value("${bp-credentials.user}")
   private String user;

   @Value("${bp-credentials.password}")
   private String password;

   @Value("${bp-credentials.secretkey}")
   private String secretKey;


   public AuthenticationResponseDto login(AuthenticationRequestDto loginDto) {

      AuthenticationResponseDto tokenDto = new AuthenticationResponseDto();

      if (!user.equals(loginDto.getUser()) || !password.equals(loginDto.getPassword())) {
         throw new CustomException(ExceptionsCode.UNAUTHORIZED.getError(), ExceptionsCode.UNAUTHORIZED);
      }

      Algorithm algorithm = Algorithm.HMAC256(secretKey);

      Date expirationTime = new Date(System.currentTimeMillis() + EXPIRATION);

      String token = JWT.create()
                           .withIssuer("auth0")
                           .withSubject("CHALLENGE SUBJECT")
                           .withClaim(user, password)
                           .withIssuedAt(new Date())
                           .withExpiresAt(expirationTime)
                           .withJWTId(UUID.randomUUID().toString())
                           .sign(algorithm);

      tokenDto.setExpiration(EXPIRATION);
      tokenDto.setToken(token);

      return tokenDto;
   }
}
