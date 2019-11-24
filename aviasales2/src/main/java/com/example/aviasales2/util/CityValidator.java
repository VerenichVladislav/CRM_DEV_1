package com.example.aviasales2.util;

import com.example.aviasales2.entity.City;
import com.example.aviasales2.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CityValidator implements Validator {
    @Autowired
    private ICityRepository cityRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return City.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        City city = (City) target;
        if (cityRepository.findByCityName(city.getCityName()) != null) {
            errors.rejectValue("cityName", "", "City with such name already exists");
        }
        if (!city.getCountry().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$")) {
            errors.rejectValue("country", "", "Bad country name!(А-Я A-Z)");
        }

        if (!city.getCityName().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$")) {
            errors.rejectValue("cityName", "", "Bad city name!(А-Я A-Z)");
        }
        if (city.getPopulation() != null && !city.getPopulation().matches("[\\d+]+$")) {
            errors.rejectValue("population", "", "Bad population value!(only positive digits!)");
        }
        if (city.getFoundationDate() != null && !city.getFoundationDate().matches("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d")) {
            errors.rejectValue("foundationDate", "", "Bad date format! (Format must be like 'dd/mm/yyyy')");
        }

    }

    public void updateValidate(Object target, Errors errors) {
        City city = (City) target;
        City city1 = cityRepository.findByCityId(city.getCityId());

        if(city.getFoundationDate() == null){
            city.setFoundationDate(city1.getFoundationDate());
        }
        if(city.getPopulation() == null){
            city.setPopulation(city1.getPopulation());
        }
        if(city.getCityName() == null){
            city.setCityName(city1.getCityName());
        }
        if(city.getCountry() == null){
            city.setCountry(city1.getCountry());
        }
        if (cityRepository.findByCityName(city.getCityName()) != null && !city.getCityName().equals(city1.getCityName())) {
            errors.rejectValue("cityName", "", "A city with the same name already exists.");
        }
        if (!city.getCountry().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$") && !city.getCountry().equals(city1.getCountry())) {
            errors.rejectValue("country", "", "Bad country name!(А-Я A-Z)");
        }

        if (!city.getCityName().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$") && !city.getCityName().equals(city1.getCityName())) {
            errors.rejectValue("cityName", "", "Bad city name!(А-Я A-Z)");
        }
        if (city.getPopulation() != null && !city.getPopulation().matches("[\\d+]+$")) {
            errors.rejectValue("population", "", "Bad population value!(only positive digits!)");
        }
        if (city.getFoundationDate() != null && !city.getFoundationDate().matches("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d")) {
            errors.rejectValue("foundationDate", "", "Bad date format! (Format must be like 'dd/mm/yyyy')");
        }
    }
}
