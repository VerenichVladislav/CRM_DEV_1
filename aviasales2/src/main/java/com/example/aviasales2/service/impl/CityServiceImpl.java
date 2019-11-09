package com.example.aviasales2.service.impl;


import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.transferObjects.CityDTO;
import com.example.aviasales2.repository.ICityRepository;
import com.example.aviasales2.service.ICityService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements ICityService {


    @Autowired
    private ICityRepository cityRepository;
    @Autowired
    DozerBeanMapper mapper;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<CityDTO> findAll() {
        return cityRepository.findAll().stream().map(entity -> mapper.map(entity, CityDTO.class)).collect(Collectors.toList());
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
    public void update(City city) {
        cityRepository.save(city);
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
