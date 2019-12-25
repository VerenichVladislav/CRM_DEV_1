package com.example.aviasales2.service.impl;


import com.example.aviasales2.entity.City;
import com.example.aviasales2.repository.ICityRepository;
import com.example.aviasales2.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements ICityService {


    private final ICityRepository cityRepository;

    @Autowired
    public CityServiceImpl(ICityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findByCityId(Long id) {
        return cityRepository.findByCityId(id);
    }

    @Override
    public String getCityName(Long id) {
        return cityRepository.findByCityId(id).getCityName();
    }

    @Override
    public List <City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findByCityName(String cityName) {
        return cityRepository.findByCityName(cityName);
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }

    @Override
    @Transactional
    public City update(City city) {
        cityRepository.save(city);
        return city;
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void deleteByCityName(String cityName) {
        cityRepository.findByCityName(cityName);
    }

}
