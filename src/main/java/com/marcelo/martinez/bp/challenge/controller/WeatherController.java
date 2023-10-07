package com.marcelo.martinez.bp.challenge.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.martinez.bp.challenge.busisness.service.WeatherService;
import com.marcelo.martinez.bp.challenge.dto.location.GenericDto;
import com.marcelo.martinez.bp.challenge.dto.location.LocationDto;
import com.marcelo.martinez.bp.challenge.dto.weather.WeatherForecastDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class WeatherController {

   private WeatherService accuWeatherService;

   @GetMapping(value = "/location/regions")
   @ApiImplicitParam(name = "accessToken", value = "token from auth/login", required = true, paramType = "header")
   @ResponseBody
   @Operation(summary = "Get", description = "Get regions available", security = @SecurityRequirement(name = "Token"))
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Get regions available", content = @Content(mediaType =
               MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", ref  = "INTERNAL_ERROR"),
         @ApiResponse(responseCode = "503", ref  = "UNAUTHORIZED"),
         @ApiResponse(responseCode = "504", ref = "GATEWAY_TIMEOUT"),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
   public ResponseEntity<GenericDto[]> getRegions() {
      GenericDto[] dto = accuWeatherService.getAllRegions();
      return new ResponseEntity<>(dto, HttpStatus.OK);
   }

   @GetMapping(value = "/location/countries")
   @ApiImplicitParam(name = "accessToken", value = "token from auth/login", required = true, paramType = "header")
   @ResponseBody
   @Operation(summary = "Get", description = "Get countries by region")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Gives the countries list", content = @Content(mediaType =
               MediaType.APPLICATION_JSON_VALUE)),
         @ApiResponse(responseCode = "500", ref = "INTERNAL_ERROR"), //
         @ApiResponse(responseCode = "504", ref = "GATEWAY_TIMEOUT"),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
   public ResponseEntity<GenericDto[]> getCountries(@RequestParam("region") String region) {
      GenericDto[] dto = accuWeatherService.getAllCountriesByRegion(region);
      return new ResponseEntity<>(dto, HttpStatus.OK);
   }

   @GetMapping(value = "/location/cities")
   @ApiImplicitParam(name = "accessToken", value = "token from auth/login", required = true, paramType = "header")
   @ResponseBody
   @Operation(summary = "Get", description = "Get cities by country and text")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Gives cities that match with the city text search", content = @Content(mediaType =
               MediaType.APPLICATION_JSON_VALUE)),
         @ApiResponse(responseCode = "500", ref = "INTERNAL_ERROR"), //
         @ApiResponse(responseCode = "504", ref = "GATEWAY_TIMEOUT"),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
   public ResponseEntity<LocationDto[]> getCities(@RequestParam("country") String country, @RequestParam("city") String city) {
      LocationDto[] dto = accuWeatherService.getCitiesByCountryAndTextCity(country, city);
      return new ResponseEntity<>(dto, HttpStatus.OK);
   }

   @GetMapping(value = "/weather")
   @ApiImplicitParam(name = "accessToken", value = "token from auth/login", required = true, paramType = "header")
   @ResponseBody
   @Operation(summary = "Get", description = "Get weather by locationId (cityId)")
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Gives weather for city", content = @Content(mediaType =
               MediaType.APPLICATION_JSON_VALUE)),
         @ApiResponse(responseCode = "500", ref = "INTERNAL_ERROR"), //
         @ApiResponse(responseCode = "504", ref = "GATEWAY_TIMEOUT"),
         @ApiResponse(responseCode = "DEFAULT", description = "Default error.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
   public ResponseEntity<WeatherForecastDto> getWeather(@RequestParam("cityId") String cityId) {
      WeatherForecastDto dto = accuWeatherService.getCityWeather(cityId);
      return new ResponseEntity<>(dto, HttpStatus.OK);
   }

}
