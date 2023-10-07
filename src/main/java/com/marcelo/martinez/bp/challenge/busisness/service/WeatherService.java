package com.marcelo.martinez.bp.challenge.busisness.service;

import java.util.Arrays;

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

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WeatherService {

   private AccuWeatherClient weatherClient;

   private RegionRepository regionRepository;

   private CountryRepository countryRepository;

   private LocationRepository locationRepository;

   private WeatherRepository weatherRepository;


   public GenericDto[] getAllRegions() {

      GenericDto[] regions = weatherClient.getRegions();

      saveRegions(regions);

      return regions;
   }

   protected void saveRegions(GenericDto[] regions) {
      Arrays.stream(regions).forEach(myRegion -> {
         Region region = LocationMapper.INSTANCE.mapRegion(myRegion);
         regionRepository.save(region);
      });
   }

   public GenericDto[] getAllCountriesByRegion(String region) {

      GenericDto[] countries = weatherClient.getAllCountriesByRegion(region);

      saveCountries(countries);

      return countries;
   }

   private void saveCountries(GenericDto[] countries) {
      Arrays.stream(countries).forEach(myCountry -> {
         Country country = LocationMapper.INSTANCE.mapCountry(myCountry);
         countryRepository.save(country);
      });
   }

   public LocationDto[] getCitiesByCountryAndTextCity(String country, String textCity) {

      LocationDto[] locations = weatherClient.getCitiesByCountryAndTextCity(country, textCity);

      saveCities(locations);

      return weatherClient.getCitiesByCountryAndTextCity(country, textCity);
   }

   private void saveCities(LocationDto[] locations) {
      Arrays.stream(locations).forEach(myCity -> {
         City city = LocationMapper.INSTANCE.mapCity(myCity);
         locationRepository.save(city);
      });
   }

   public WeatherForecastDto getCityWeather(String cityId) {

      WeatherForecastDto weatherForecastDto = weatherClient.getWeather(cityId);

      saveWeather(cityId, weatherForecastDto);

      return weatherForecastDto;
   }

   private void saveWeather(String cityId, WeatherForecastDto weatherForecastDto) {
      Weather weather = WeatherMapper.INSTANCE.mapWeather(weatherForecastDto);
      weather.setCityId(cityId);

      weatherRepository.save(weather);
   }

}
