package com.example.aviasales2.controller;


import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.transferObjects.CityDTO;
import com.example.aviasales2.service.ICityService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
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
    public CityDTO findById(@PathVariable("id") Long id){
        return mapper.map(cityService.findById(id), CityDTO.class);
    }

    @GetMapping("/")
    public CityDTO findByName(@RequestParam String cityName ){
        return mapper.map(cityService.findByCityName(cityName), CityDTO.class);
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

