package com.marcelo.martinez.bp.challenge.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.martinez.bp.challenge.exception.ExceptionsCode;

@Component
public class BPFilter extends OncePerRequestFilter {

   @Value("${bp-credentials.user}")
   private String user;

   @Value("${bp-credentials.password}")
   private String password;

   @Value("${bp-credentials.secretkey}")
   private String secretKey;

   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {

      try {
         String headerToken = request.getHeader("accessToken");

         Algorithm algorithm = Algorithm.HMAC256(secretKey);
         JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").withClaim(user, password).build();
         verifier.verify(headerToken);
         filterChain.doFilter(request, response);

      } catch (IllegalArgumentException | JWTVerificationException | IOException | ServletException | NullPointerException e) {
         this.buildResponseError(response);
      }
   }

   private void buildResponseError(HttpServletResponse response) throws IOException {
      response.setStatus(ExceptionsCode.UNAUTHORIZED.getHttpStatus().value());
      response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
      new ObjectMapper().writeValue(response.getWriter(), ExceptionsCode.UNAUTHORIZED);
      response.getWriter().flush();
   }
}
