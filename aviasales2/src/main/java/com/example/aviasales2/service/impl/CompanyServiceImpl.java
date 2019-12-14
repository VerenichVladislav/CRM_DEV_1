package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.CompanyFilter;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.QCompany;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.service.CompanyService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    final
    CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findByCompanyId(long id) {
        return companyRepository.findByCompanyId(id);
    }


    @Override
    public void delete(long id) {
        companyRepository.delete(companyRepository.findByCompanyId(id));
    }

    @Override
    public void deleteByCompanyName(String name) {
        companyRepository.deleteByCompanyName(name);
    }

    @Override
    public Company findByCompanyName(String name) {
        return companyRepository.findByCompanyName(name);
    }

    @Override
    public List <Company> findByRating(int rating) {
        return companyRepository.findByRating(rating);
    }

    @Override
    public List <Company> findByCompanyNameAndRating(String name, int rating) {
        return companyRepository.findByCompanyNameAndRating(name, rating);
    }

    @Override
    public List <Company> findAll(CompanyFilter companyFilter) {
        final QCompany qCompany = QCompany.company;
        BooleanBuilder booleanBuilder = new BooleanBuilder(qCompany.isNotNull());
        if (companyFilter.getCompanyName() != null) {
            booleanBuilder.and(qCompany.companyName.eq(companyFilter.getCompanyName()));
        }
        if (companyFilter.getRating() != 0) {
            booleanBuilder.and(qCompany.rating.eq(companyFilter.getRating()));
        }
        return (List <Company>) companyRepository.findAll(booleanBuilder);
    }


}
