package com.marcelo.martinez.bp.challenge.busisness.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.martinez.bp.challenge.busisness.client.AccuWeatherClient;
import com.marcelo.martinez.bp.challenge.dto.location.GenericDto;
import com.marcelo.martinez.bp.challenge.dto.location.LocationDto;
import com.marcelo.martinez.bp.challenge.dto.weather.WeatherForecastDto;
import com.marcelo.martinez.bp.challenge.mapper.LocationMapper;
import com.marcelo.martinez.bp.challenge.mapper.WeatherMapper;
import com.marcelo.martinez.bp.challenge.model.entity.City;
import com.marcelo.martinez.bp.challenge.model.entity.Country;
import com.marcelo.martinez.bp.challenge.model.entity.Region;
import com.marcelo.martinez.bp.challenge.model.entity.Weather;
import com.marcelo.martinez.bp.challenge.model.repository.CountryRepository;
import com.marcelo.martinez.bp.challenge.model.repository.LocationRepository;
import com.marcelo.martinez.bp.challenge.model.repository.RegionRepository;
import com.marcelo.martinez.bp.challenge.model.repository.WeatherRepository;
import com.marcelo.martinez.bp.challenge.util.ContextFactory;

@Service
public class AccuWeatherService {

   @Autowired
   private AccuWeatherClient weatherClient;

   public GenericDto[] getAllRegions() {

      GenericDto[] regions = weatherClient.getRegions();

      RegionRepository regionRepository = ContextFactory.getBean(RegionRepository.class);
      Arrays.stream(regions).forEach(myRegion -> {
         Region region = LocationMapper.INSTANCE.mapRegion(myRegion);
         regionRepository.save(region);
      });

      return regions;
   }

   public GenericDto[] getAllCountriesByRegion(String region) {

      GenericDto[] countries = weatherClient.getAllCountriesByRegion(region);

      CountryRepository countryRepository = ContextFactory.getBean(CountryRepository.class);
      Arrays.stream(countries).forEach(myCountry -> {
         Country country = LocationMapper.INSTANCE.mapCountry(myCountry);
         countryRepository.save(country);
      });

      return countries;
   }

   public LocationDto[] getCitiesByCountryAndTextCity(String country, String textCity) {

      LocationDto[] locations = weatherClient.getCitiesByCountryAndTextCity(country, textCity);

      LocationRepository locationRepository = ContextFactory.getBean(LocationRepository.class);
      Arrays.stream(locations).forEach(myCity -> {
         City city = LocationMapper.INSTANCE.mapCity(myCity);
         locationRepository.save(city);
      });

      return weatherClient.getCitiesByCountryAndTextCity(country, textCity);
   }

   public WeatherForecastDto getCityWeather(String cityId) {

      WeatherForecastDto weatherForecastDto = weatherClient.getWeather(cityId);

      WeatherRepository locationRepository = ContextFactory.getBean(WeatherRepository.class);
      Weather weather = WeatherMapper.INSTANCE.mapWeather(weatherForecastDto);
      weather.setCityId(cityId);

      locationRepository.save(weather);

      return weatherForecastDto;
   }

}
