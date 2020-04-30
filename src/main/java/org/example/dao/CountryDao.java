package org.example.dao;

import org.example.model.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getContinent(String continent);
    List<Country> getRegion(String region);
    Country getByName(String name);
    Country getByCode(String code);
    Country create(Country country);
}
