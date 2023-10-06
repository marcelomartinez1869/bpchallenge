package com.marcelo.martinez.bp.challenge.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.marcelo.martinez.bp.challenge.model.entity.Weather;

public interface WeatherRepository extends CrudRepository<Weather, Integer> {

}
