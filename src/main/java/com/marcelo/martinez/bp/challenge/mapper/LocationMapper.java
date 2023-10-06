package com.marcelo.martinez.bp.challenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.marcelo.martinez.bp.challenge.dto.location.GenericDto;
import com.marcelo.martinez.bp.challenge.dto.location.LocationDto;
import com.marcelo.martinez.bp.challenge.model.entity.City;
import com.marcelo.martinez.bp.challenge.model.entity.Country;
import com.marcelo.martinez.bp.challenge.model.entity.Region;

@Mapper
public interface LocationMapper {

   LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

   @Mapping(target = "regionId", source = "dto.id")
   @Mapping(target = "localizedName", source = "dto.localizedName")
   @Mapping(target = "englishName", source = "dto.englishName")
   Region mapRegion(GenericDto dto);

   @Mapping(target = "countryId", source = "dto.id")
   @Mapping(target = "localizedName", source = "dto.localizedName")
   @Mapping(target = "englishName", source = "dto.englishName")
   Country mapCountry(GenericDto dto);

   @Mapping(target = "cityId", source = "dto.key")
   @Mapping(target = "localizedName", source = "dto.localizedName")
   @Mapping(target = "englishName", source = "dto.englishName")
   @Mapping(target = "type", source = "dto.type")
   @Mapping(target = "rank", source = "dto.rank")
   @Mapping(target = "primaryPostalCode", source = "dto.primaryPostalCode")
   @Mapping(target = "regionId", source = "dto.region.id")
   @Mapping(target = "countryId", source = "dto.country.id")
   City mapCity(LocationDto dto);
}
