package com.example.aviasales2.controller;


import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.transferObjects.CityDTO;
import com.example.aviasales2.service.ICityService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private ICityService cityService;
    @Autowired
    private DozerBeanMapper mapper;


    @GetMapping
    public List<CityDTO> findAll(){
        return cityService.findAll().stream().map(entity -> mapper.map(entity, CityDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<City> findById(@PathVariable("id") Long id){
        return cityService.findById(id);
    }

    @GetMapping("/")
    public City findByName(@RequestParam String cityName ){
        return cityService.findByCityName(cityName);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        cityService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteByCityName(@RequestParam String cityName){
        cityService.deleteByCityName(cityName);
    }

    @DeleteMapping
    public void delete(@RequestBody City city){
        cityService.delete(city);
    }

    @PostMapping
    public City save(@RequestBody City city){
        return cityService.save(city);
    }

    @PutMapping
    public void update(@RequestBody City newCity){
        cityService.update(newCity);
    }
}

