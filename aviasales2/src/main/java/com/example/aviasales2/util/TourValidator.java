package com.example.aviasales2.util;

import com.example.aviasales2.entity.transferObjects.TourDTO;
import com.example.aviasales2.repository.TourRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TourValidator implements Validator {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public boolean supports(Class <?> aClass) {
        return TourDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TourDTO tourDTO = (TourDTO) target;
        if (tourRepository.findByName(tourDTO.getName()) != null) {
            errors.rejectValue("name", "", "The tour with such name already exists!");
        }

    }

    public void updateValidate(Object target, Errors errors) {
        TourDTO tourDTO = (TourDTO) target;
        TourDTO tourDTO1 = mapper.map(tourRepository.findByTourId(tourDTO.getTourId()), TourDTO.class);
        if (tourDTO.getRating() == null) {
            tourDTO.setRating(tourDTO1.getRating());
        }
        if (tourRepository.findByName(tourDTO.getName()) != null && !tourDTO.getName().equals(tourDTO1.getName())) {
            errors.rejectValue("name", "", "The tour with such name already exists!");
        }
    }
}
