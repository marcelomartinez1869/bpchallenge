package com.marcelo.martinez.bp.challenge.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Weather {

   @Id
   @Column(nullable = false)
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long weatherId;

   @Column
   private String weatherDate;

   @Column
   private String description;

   @Column
   private String mobileLink;

   @Column
   private String link;

   @Column
   private String cityId;

}
