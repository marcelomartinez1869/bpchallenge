package com.marcelo.martinez.bp.challenge.busisness.client;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.marcelo.martinez.bp.challenge.exception.ExceptionsCode;
import com.marcelo.martinez.bp.challenge.exception.CustomException;
import com.marcelo.martinez.bp.challenge.dto.location.GenericDto;
import com.marcelo.martinez.bp.challenge.dto.location.LocationDto;
import com.marcelo.martinez.bp.challenge.dto.weather.WeatherForecastDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class   AccuWeatherClient {

   public static final String PROVIDER_KEY_PARAM = "apikey";

   public static final String Q_PARAM = "q";

   private static final String GET_REGIONS_PATH = "/locations/v1/regions";

   private static final String GET_COUNTRIES_PATH = "/locations/v1/countries/";

   private static final String GET_CITIES_PATH = "/locations/v1/cities/{0}/search";

   private static final String GET_WEATHER_PATH = "/forecasts/v1/daily/1day/{0}";

   private static final int TIMEOUT = 10;

   @Value("${secrets.accuweather.apiurl}")
   private String apiUrl;

   @Value("${secrets.accuweather.apikey}")
   private String apiKey;

   public GenericDto[] getRegions() {

      WebClient client = WebClient
            .builder()
            .baseUrl(apiUrl)
            .build();

      return client
            .get()
            .uri(uriBuilder -> uriBuilder.path(GET_REGIONS_PATH).queryParam(PROVIDER_KEY_PARAM, apiKey).build())
            .retrieve()
            .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class).flatMap(errorBody -> {
               if (response.statusCode().is5xxServerError()) {
                  return Mono.error(new CustomException(errorBody, ExceptionsCode.INTERNAL_ERROR));
               }
               return Mono.error(new CustomException(errorBody, ExceptionsCode.BAD_REQUEST));
            }))
            .bodyToMono(GenericDto[].class)
            .timeout(Duration.ofSeconds(TIMEOUT))
            .doOnError(error -> {
               if (error instanceof TimeoutException) {
                  log.error("TimeOut {}", error.getMessage());
                  throw new CustomException("Timeout", ExceptionsCode.GATEWAY_TIMEOUT);
               } else {
                  if (error instanceof CustomException) {
                     throw (CustomException) error;
                  }
                  log.error("Error getting Regions {}", error.getMessage());
                  throw new CustomException(error.getMessage(), ExceptionsCode.INTERNAL_ERROR, error);
               }
            })
            .block();
   }

   public GenericDto[] getAllCountriesByRegion(String region) {

      WebClient client = WebClient
            .builder()
            .baseUrl(apiUrl)
            .build();

      return client
            .get()
            .uri(uriBuilder -> uriBuilder.path(GET_COUNTRIES_PATH + "/" + region)
               .queryParam(PROVIDER_KEY_PARAM, apiKey).build())
            .retrieve()
            .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class).flatMap(errorBody -> {
               if (response.statusCode().is5xxServerError()) {
                  return Mono.error(new CustomException(errorBody, ExceptionsCode.INTERNAL_ERROR));
               }
               return Mono.error(new CustomException(errorBody, ExceptionsCode.BAD_REQUEST));
            }))
            .bodyToMono(GenericDto[].class)
            .timeout(Duration.ofSeconds(TIMEOUT))
            .doOnError(error -> {
               if (error instanceof TimeoutException) {
                  log.error("TimeOut {}", error.getMessage());
                  throw new CustomException("Timeout", ExceptionsCode.GATEWAY_TIMEOUT);
               } else {
                  if (error instanceof CustomException) {
                     throw (CustomException) error;
                  }
                  log.error("Error getting Countries {}", error.getMessage());
                  throw new CustomException(error.getMessage(), ExceptionsCode.INTERNAL_ERROR, error);
               }
            })
            .block();
   }

   public LocationDto[] getCitiesByCountryAndTextCity(String country, String city) {

      WebClient client = WebClient
            .builder()
            .baseUrl(apiUrl)
            .build();

      return client
            .get()
            .uri(uriBuilder -> uriBuilder.path(MessageFormat.format(GET_CITIES_PATH, country))
               .queryParam(PROVIDER_KEY_PARAM, apiKey)
               .queryParam(Q_PARAM, city).build())
            .retrieve()
            .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class).flatMap(errorBody -> {
               if (response.statusCode().is5xxServerError()) {
                  return Mono.error(new CustomException(errorBody, ExceptionsCode.INTERNAL_ERROR));
               }
               return Mono.error(new CustomException(errorBody, ExceptionsCode.BAD_REQUEST));
            }))
            .bodyToMono(LocationDto[].class)
            .timeout(Duration.ofSeconds(TIMEOUT))
            .doOnError(error -> {
               if (error instanceof TimeoutException) {
                  log.error("TimeOut {}", error.getMessage());
                  throw new CustomException("Timeout", ExceptionsCode.GATEWAY_TIMEOUT);
               } else {
                  if (error instanceof CustomException) {
                     throw (CustomException) error;
                  }
                  log.error("Error getting City by text {}", error.getMessage());
                  throw new CustomException(error.getMessage(), ExceptionsCode.INTERNAL_ERROR, error);
               }
            })
            .block();
   }

   public WeatherForecastDto getWeather(String cityId) {

      WebClient client = WebClient
            .builder()
            .baseUrl(apiUrl)
            .build();

      return client
            .get()
            .uri(uriBuilder -> uriBuilder.path(MessageFormat.format(GET_WEATHER_PATH, cityId)).queryParam(PROVIDER_KEY_PARAM, apiKey).build())
            .retrieve()
            .onStatus(HttpStatus::isError, response -> response.bodyToMono(String.class).flatMap(errorBody -> {
               if (response.statusCode().is5xxServerError()) {
                  return Mono.error(new CustomException(errorBody, ExceptionsCode.INTERNAL_ERROR));
               }
               return Mono.error(new CustomException(errorBody, ExceptionsCode.BAD_REQUEST));
            }))
            .bodyToMono(WeatherForecastDto.class)
            .timeout(Duration.ofSeconds(TIMEOUT))
            .doOnError(error -> {
               if (error instanceof TimeoutException) {
                  log.error("TimeOut {}", error.getMessage());
                  throw new CustomException("Timeout", ExceptionsCode.GATEWAY_TIMEOUT);
               } else {
                  if (error instanceof CustomException) {
                     throw (CustomException) error;
                  }
                  log.error("Error getting weather{}", error.getMessage());
                  throw new CustomException(error.getMessage(), ExceptionsCode.INTERNAL_ERROR, error);
               }
            })
            .block();
   }

}
