package com.example.aviasales2.util;

import com.example.aviasales2.entity.transferObjects.TransportDTO;
import com.example.aviasales2.repository.TransportRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TransportValidator implements Validator {
    @Autowired
    private TransportRepository transportRepository;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public boolean supports(Class <?> aClass) {
        return TransportDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransportDTO transport = (TransportDTO) target;
        TransportDTO transport1 = mapper.map(transportRepository.findByTransportId(transport.getTransportId()), TransportDTO.class);
        if (transport.getCompany() == 0) {
            transport.setCompany(transport1.getCompany());
        }
    }
}
