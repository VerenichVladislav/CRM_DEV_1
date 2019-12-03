package com.example.aviasales2.util;

import com.example.aviasales2.entity.transferObjects.CityDTO;
import com.example.aviasales2.repository.ICityRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CityValidator implements Validator {
    @Autowired
    private ICityRepository cityRepository;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public boolean supports(Class <?> aClass) {
        return CityDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CityDTO city = (CityDTO) target;
        if (cityRepository.findByCityName(city.getCityName()) != null) {
            errors.rejectValue("cityName", "", "City with such name already exists");
        }
//        if (!city.getCountry().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$")) {
//            errors.rejectValue("country", "", "Bad country name!(А-Я A-Z)");
//        }

//        if (!city.getCityName().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$")) {
//            errors.rejectValue("cityName", "", "Bad city name!(А-Я A-Z)");
//        }
//        if (city.getPopulation() != null && !city.getPopulation().matches("[\\d+]+$")) {
//            errors.rejectValue("population", "", "Bad population value!(only positive digits!)");
//        }
    }

    public void updateValidate(Object target, Errors errors) {
        CityDTO city = (CityDTO) target;
        CityDTO city1 = mapper.map(cityRepository.findByCityId(city.getCityId()), CityDTO.class);

        if (city.getPopulation() == null) {
            city.setPopulation(city1.getPopulation());
        }
        if (cityRepository.findByCityName(city.getCityName()) != null && !city.getCityName().equals(city1.getCityName())) {
            errors.rejectValue("cityName", "", "A city with the same name already exists.");
        }
//        if (!city.getCountry().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$") && !city.getCountry().equals(city1.getCountry())) {
//            errors.rejectValue("country", "", "Bad country name!(А-Я A-Z)");
//        }
//
//        if (!city.getCityName().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$") && !city.getCityName().equals(city1.getCityName())) {
//            errors.rejectValue("cityName", "", "Bad city name!(А-Я A-Z)");
//        }
//        if (city.getPopulation() != null && !city.getPopulation().matches("[\\d+]+$")) {
//            errors.rejectValue("population", "", "Bad population value!(only positive digits!)");
//        }
    }
}
