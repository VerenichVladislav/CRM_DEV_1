package com.example.aviasales2.util;

import com.example.aviasales2.entity.transferObjects.HotelDTO;
import com.example.aviasales2.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HotelValidator implements Validator {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public boolean supports(Class<?> aClass) {
        return HotelDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HotelDTO hotel = (HotelDTO) target;
//        if(!hotel.getCountry().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$")){
//            errors.rejectValue("country", "", "Bad country name!");
//        }
//        if(!hotel.getAddress().matches("^[а-яА-ЯёЁa-zA-Z0-9\\s]+$")){
//            errors.rejectValue("address", "", "Bad address name");
//        }
        if(hotelRepository.findByHotelName(hotel.getHotelName())!=null){
            errors.rejectValue("hotelName", "", "The hotel with such name already exists");
        }
//        if(hotel.getRating() != null && !hotel.getRating().matches("[0-5]")){
//            errors.rejectValue("rating", "", "Bad rating number!(0-5)");
//        }
//        if(hotel.getCity() !=null &&!hotel.getCity().matches("^[а-яА-ЯёЁa-zA-Z\\s]+$")){
//            errors.rejectValue("city", "", "Bad city name");
//        }
//        if (hotel.getPhoneNumber()!=null && !hotel.getPhoneNumber().matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")){
//            errors.rejectValue("phoneNumber", "", "Bad phone number");
//        }
    }
}
