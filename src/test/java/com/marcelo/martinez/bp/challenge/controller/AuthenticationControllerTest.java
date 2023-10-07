package com.marcelo.martinez.bp.challenge.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.marcelo.martinez.bp.challenge.busisness.service.AuthenticationService;
import com.marcelo.martinez.bp.challenge.dto.authentication.AuthenticationRequestDto;
import com.marcelo.martinez.bp.challenge.exception.CustomException;
import com.marcelo.martinez.bp.challenge.exception.ExceptionsCode;

@SpringBootTest
class AuthenticationControllerTest {

   @MockBean
   AuthenticationService authenticationService;

   @SpyBean
   AuthenticationController authenticationController;

   @Test
   void login() {
      Mockito
            .when(authenticationService.login(Mockito.any()))
            .thenThrow(new CustomException(ExceptionsCode.UNAUTHORIZED.getError(), ExceptionsCode.UNAUTHORIZED));
      assertThrows(CustomException.class, ()-> authenticationController.login(new AuthenticationRequestDto()));
      Mockito.verify(authenticationService, Mockito.times(1)).login(Mockito.any());
   }
}
