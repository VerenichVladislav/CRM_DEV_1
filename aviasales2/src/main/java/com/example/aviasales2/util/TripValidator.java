package com.example.aviasales2.util;

import com.example.aviasales2.entity.transferObjects.TripDTO;
import com.example.aviasales2.repository.TripRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TripValidator implements Validator {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public boolean supports(Class <?> aClass) {
        return TripDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public void updateValidate(Object target, Errors errors) {
        TripDTO tripDTO = (TripDTO) target;
        TripDTO tripDTO1 = mapper.map(tripRepository.findByTripId(tripDTO.getTripId()), TripDTO.class);
        if (tripDTO.getDateFrom() == null) {
            tripDTO.setDateFrom(tripDTO1.getDateFrom());
        }
        if (tripDTO.getDateDest() == null) {
            tripDTO.setDateDest(tripDTO1.getDateDest());
        }
    }
}
