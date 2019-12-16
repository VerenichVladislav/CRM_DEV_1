package com.example.aviasales2.service;

import com.example.aviasales2.config.filterConfig.CompanyFilter;
import com.example.aviasales2.entity.Company;

import java.util.List;

public interface CompanyService {
    Company save(Company company);

    Company findByCompanyId(long id);

    void delete(long id);

    void deleteByCompanyName(String name);

    Company findByCompanyName(String name);

    List <Company> findByRating(int rating);

    List <Company> findByCompanyNameAndRating(String name, int rating);

    List <Company> findAll(CompanyFilter companyFilter, Integer pageNo, Integer pageSize, String sortBy);
}
