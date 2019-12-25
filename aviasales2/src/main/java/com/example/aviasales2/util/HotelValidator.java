package com.example.aviasales2.util;

import com.example.aviasales2.entity.transferObjects.HotelDTO;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HotelValidator implements Validator {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ICityRepository cityRepository;

    @Override
    public boolean supports(Class <?> aClass) {
        return HotelDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HotelDTO hotel = (HotelDTO) target;
        if (hotelRepository.findByHotelName(hotel.getHotelName()) != null) {
            errors.rejectValue("hotelName", "", "The hotel with such name already exists");
        }
        if (hotel.getCityId() == null || cityRepository.findByCityId(hotel.getCityId()) == null) {
            errors.rejectValue("cityId", "", "No such City");
        }
    }
}
