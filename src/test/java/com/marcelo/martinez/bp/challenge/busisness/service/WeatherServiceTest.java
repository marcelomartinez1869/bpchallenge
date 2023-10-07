package com.marcelo.martinez.bp.challenge.busisness.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.martinez.bp.challenge.busisness.client.AccuWeatherClient;
import com.marcelo.martinez.bp.challenge.dto.location.GenericDto;

@SpringBootTest
class WeatherServiceTest {

   @MockBean
   AccuWeatherClient client;

   @SpyBean
   WeatherService weatherService;

   @Test
   void getAllRegions() throws JsonProcessingException {
      GenericDto[] dto = new ObjectMapper ().readValue("[{\"LocalizedName\":\"Africa\",\"EnglishName\":\"Africa\",\"ID\":\"AFR\"},"
            + "{\"LocalizedName\":\"Antarctica\",\"EnglishName\":\"Antarctica\",\"ID\":\"ANT\"}]", GenericDto[].class);
      Mockito.when(client.getRegions()).thenReturn(dto);
      GenericDto[] returnDto = weatherService.getAllRegions();
      Mockito.verify(client, Mockito.times(1)).getRegions();
      Mockito.verify(weatherService, Mockito.times(1)).saveRegions(dto);
      assertEquals(dto, returnDto);
   }
}
