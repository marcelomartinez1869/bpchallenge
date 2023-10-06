package com.marcelo.martinez.bp.challenge.busisness.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.marcelo.martinez.bp.challenge.api.ExceptionsCode;
import com.marcelo.martinez.bp.challenge.busisness.exception.CustomException;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationRequestDto;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationResponseDto;

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
         throw new CustomException("unauthorized", ExceptionsCode.UNAUTHORIZED);
      }

      Map<String, String> payLoad = new HashMap<>();
      payLoad.put("user", user);
      payLoad.put("pass", password);

      Algorithm algorithm = Algorithm.HMAC256(secretKey);

      Date expirationTime = new Date(System.currentTimeMillis() + EXPIRATION);

      String token = JWT
            .create()
            .withIssuer("auth0")
            .withPayload(payLoad)
            .withClaim(user, password)
            .withExpiresAt(expirationTime)
            .sign(algorithm);

      tokenDto.setExpiration(EXPIRATION);
      tokenDto.setToken(token);

      return tokenDto;
   }
}
