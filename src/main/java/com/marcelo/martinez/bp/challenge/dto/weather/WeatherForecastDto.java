package com.marcelo.martinez.bp.challenge.dto.weather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class WeatherForecastDto {

   private Headline headline;

   private List<DailyForecast> dailyForecasts;

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class Headline {

      private String effectiveDate;

      private Integer effectiveEpochDate;

      private Integer severity;

      private String text;

      private String category;

      private String endDate;

      private String endEpochDate;

      private String mobileLink;

      private String link;
   }

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class DailyForecast {

      private String date;

      private Integer epochDate;

      private Temperature temperature;

      private DayWeather day;

      private NightWeather night;

      private List<String> sources;

      private String mobileLink;

      private String link;
   }

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class Temperature {

      private Value minimum;

      private Value maximum;

      @Data
      @JsonIgnoreProperties(ignoreUnknown = true)
      @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
      public static class Value {

         private Integer value;

         private String unit;

         private Integer unitType;
      }
   }

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class DayWeather {

      private Integer icon;

      private String iconPhrase;

      private Boolean hasPrecipitation;
   }

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class NightWeather {

      private Integer icon;

      private String iconPhrase;

      private Boolean hasPrecipitation;
   }
}
