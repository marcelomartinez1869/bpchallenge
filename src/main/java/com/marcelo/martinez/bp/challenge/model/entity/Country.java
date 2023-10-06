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
public class Country {

   @Id
   @Column(nullable = false)
   private String countryId;

   @Column
   private String localizedName;

   @Column
   private String englishName;

}
