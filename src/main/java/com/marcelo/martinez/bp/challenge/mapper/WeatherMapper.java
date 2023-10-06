package com.marcelo.martinez.bp.challenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.marcelo.martinez.bp.challenge.dto.weather.WeatherForecastDto;
import com.marcelo.martinez.bp.challenge.model.entity.Weather;

@Mapper
public interface WeatherMapper {

   WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

   @Mapping(target = "weatherDate", source = "dto.headline.effectiveDate")
   @Mapping(target = "description", source = "dto.headline.text")
   @Mapping(target = "mobileLink", source = "dto.headline.mobileLink")
   @Mapping(target = "link", source = "dto.headline.link")
   Weather mapWeather(WeatherForecastDto dto);
}
