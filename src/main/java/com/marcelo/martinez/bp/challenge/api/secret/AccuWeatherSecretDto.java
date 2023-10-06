package com.marcelo.martinez.bp.challenge.api.secret;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccuWeatherSecretDto {

   @Value("${secrets.accuweather.apiurl}")
   private String apiUrl;

   @Value("${secrets.accuweather.apikey}")
   private String apikey;

   @Override
   public int hashCode() {
      return Objects.hash(apiUrl, apikey);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      AccuWeatherSecretDto that = (AccuWeatherSecretDto) o;
      return Objects.equals(apiUrl, that.apiUrl) && Objects.equals(apikey, that.apikey);
   }

}
