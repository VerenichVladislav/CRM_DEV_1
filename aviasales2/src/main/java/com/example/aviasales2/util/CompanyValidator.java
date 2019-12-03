package com.example.aviasales2.util;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.transferObjects.CompanyDTO;
import com.example.aviasales2.repository.CompanyRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompanyValidator implements Validator {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public boolean supports(Class <?> aClass) {
        return Company.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CompanyDTO company = (CompanyDTO) target;

        if (companyRepository.findByCompanyName(company.getCompanyName()) != null) {
            errors.rejectValue("companyName", "", "A company with the same name already exists");
        }
    }

    public void updateValidate(Object target, Errors errors) {
        CompanyDTO company = (CompanyDTO) target;
        CompanyDTO company2 = mapper.map(companyRepository.findByCompanyId(company.getCompanyId()), CompanyDTO.class);
        if (companyRepository.findByCompanyName(company.getCompanyName()) != null && !company.getCompanyName().equals(company2.getCompanyName())) {
            errors.rejectValue("companyName", "", "A company with the same name already exists");
        }
        if (company.getRating() == null) {
            company.setRating(company2.getRating());
        }
        company.setTransportCount(company2.getTransportCount());

    }
}
