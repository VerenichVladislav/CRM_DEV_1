package com.example.aviasales2.controller;


import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.transferObjects.CityDTO;
import com.example.aviasales2.exception.GlobalBadRequestException;
import com.example.aviasales2.exception.NoSuchEntityException;
import com.example.aviasales2.service.ICityService;
import com.example.aviasales2.util.CityValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/cities")
public class CityController {

    private final ICityService cityService;
    private final DozerBeanMapper mapper;
    private final CityValidator cityValidator;

    @Autowired
    public CityController(ICityService cityService, DozerBeanMapper mapper, CityValidator cityValidator) {
        this.cityService = cityService;
        this.mapper = mapper;
        this.cityValidator = cityValidator;
    }


    @GetMapping
    public List <CityDTO> findAll() {
        return cityService.findAll().stream().map(entity -> mapper.map(entity, CityDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CityDTO findById(@PathVariable("id") Long id) {
        return mapper.map(cityService.findByCityId(id), CityDTO.class);
    }

    @GetMapping("/")
    public CityDTO findByName(@RequestParam String cityName) {
        return mapper.map(cityService.findByCityName(cityName), CityDTO.class);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
    }

    @DeleteMapping("/")
    public void deleteByCityName(@RequestParam String cityName) {
        cityService.deleteByCityName(cityName);
    }

    @DeleteMapping
    public void delete(@RequestBody City city) {
        cityService.delete(city);
    }

    @PostMapping
    public ResponseEntity <CityDTO> save(@RequestBody @Valid CityDTO city, BindingResult result) {
        cityValidator.validate(city, result);
        if (result.hasErrors()) {
            throw new GlobalBadRequestException(result);
        }
        CityDTO body = mapper.map(cityService.save(mapper.map(city, City.class)), CityDTO.class);
        return new ResponseEntity <>(body, HttpStatus.OK);
    }

    @GetMapping("/name/{id}")
    public ResponseEntity <String> getCityName(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.getCityName(id));
    }

    @PutMapping
    public ResponseEntity <CityDTO> update(@RequestBody @Valid CityDTO newCity, BindingResult result) {
        if (cityService.findByCityId(newCity.getCityId()) != null) {
            cityValidator.updateValidate(newCity, result);
            if (result.hasErrors()) {
                throw new GlobalBadRequestException(result);
            }
            CityDTO body = mapper.map(cityService.update(mapper.map(newCity, City.class)), CityDTO.class);
            return new ResponseEntity <>(body, HttpStatus.OK);
        }
        throw new NoSuchEntityException(City.class);

    }
}

