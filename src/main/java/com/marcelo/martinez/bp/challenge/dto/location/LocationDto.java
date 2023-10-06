package com.marcelo.martinez.bp.challenge.dto.location;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class LocationDto {

   private Integer version;

   private String key;

   private String type;

   private Integer rank;

   private String localizedName;

   private String englishName;

   private String primaryPostalCode;

   private GenericDto region;

   private GenericDto country;

   private AdministrativeArea administrativeArea;

   private TimeZone timeZone;

   private GeoPosition geoPosition;

   private Boolean isAlias;

   private List<SupplementalAdminArea> supplementalAdminAreas;

   private List<String> dataSets;

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class AdministrativeArea {

      private String id;

      private String localizedName;

      private String englishName;

      private Integer level;

      private String localizedType;

      private String englishType;

      private String countryId;
   }

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class TimeZone {

      private String code;

      private String name;

      private Integer gmtOffset;

      private boolean isDaylightSaving;
   }

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class GeoPosition {

      private Double latitude;

      private Double longitude;

      private Elevation elevation;

      @Data
      @JsonIgnoreProperties(ignoreUnknown = true)
      @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
      public static class Elevation {

         private Metric metric;

         private Imperial imperial;

         @Data
         @JsonIgnoreProperties(ignoreUnknown = true)
         @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
         public static class Metric {

            private Double value;

            private String unit;

            private Integer unitType;
         }

         @Data
         @JsonIgnoreProperties(ignoreUnknown = true)
         @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
         public static class Imperial {

            private Double value;

            private String unit;

            private Integer unitType;
         }
      }
   }

   @Data
   @JsonIgnoreProperties(ignoreUnknown = true)
   @JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
   public static class SupplementalAdminArea {

      private Integer level;

      private String localizedName;

      private String englishName;
   }
}
