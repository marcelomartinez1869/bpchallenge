package com.marcelo.martinez.bp.challenge.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.marcelo.martinez.bp.challenge.model.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {

}
