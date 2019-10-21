package com.example.aviasales2.service;


import com.example.aviasales2.entity.City;

import java.util.List;
import java.util.Optional;

public interface ICityService {

    City save(City city);
    Optional<City> findById(Long id);
    List<City> findAll();
    City findByCityName(String name);
    void delete(City city);
    void update(City city);
    void deleteById(Long id);
    void deleteByCityName(String name);

}