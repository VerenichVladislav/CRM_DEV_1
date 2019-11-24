package com.example.aviasales2.util;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CompanyValidator implements Validator {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Company.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Company company = (Company) target;

        if(companyRepository.findByCompanyName(company.getCompanyName()) != null)
        {
            errors.rejectValue("companyName", "","A company with the same name already exists.");
        }

        if (!company.getRating().matches("[\\d]") || Integer.parseInt(company.getRating()) < 0 || Integer.parseInt(company.getRating()) > 5) {
            errors.rejectValue("rating", "", "Bad rating number(0-5).");
        }


    }
}
