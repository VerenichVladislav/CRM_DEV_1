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

        if (companyRepository.findByCompanyName(company.getCompanyName()) != null) {
            errors.rejectValue("companyName", "", "A company with the same name already exists.");
        }

        if (company.getRating() != null && !company.getRating().matches("[0-5]")) {
            errors.rejectValue("rating", "", "Bad rating number(0-5).");
        }
    }

    public void updateValidate(Object target, Errors errors) {
        Company company = (Company) target;
        Company company2 = companyRepository.findByCompanyId(company.getCompanyId());
        if (companyRepository.findByCompanyName(company.getCompanyName()) != null && !company.getCompanyName().equals(company2.getCompanyName())) {
            errors.rejectValue("companyName", "", "A company with the same name already exists.");
        }

        if (company.getRating() != null && !company.getRating().matches("[0-5]")) {
            errors.rejectValue("rating", "", "Bad rating number(0-5).");
        }
        if(company.getRating() == null){
            company.setRating(company2.getRating());
        }

    }
}
