package com.example.aviasales2.service;


import com.example.aviasales2.entity.City;

import java.util.List;

public interface ICityService {

    City save(City city);

    City findByCityId(Long id);

    List <City> findAll();

    City findByCityName(String name);

    void delete(City city);

    City update(City city);

    void deleteById(Long id);

    void deleteByCityName(String name);

    String getCityName(Long id);
}
