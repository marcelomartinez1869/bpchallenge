package com.marcelo.martinez.bp.challenge.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City {

   @Id
   @Column(nullable = false)
   private String cityId;

   @Column
   private String localizedName;

   @Column
   private String englishName;

   @Column
   private String type;

   @Column
   private Integer rank;

   @Column
   private String primaryPostalCode;

   @Column
   private String regionId;

   @Column
   private String countryId;

}
