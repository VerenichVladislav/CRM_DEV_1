package com.example.aviasales2.service;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.transferObjects.CompanyDTO;

import java.util.List;

public interface CompanyService {
    Company save(Company company);

    Company findByCompanyId(long id);

    void delete(long id);

    Company findByCompanyName(String name);

    List<Company> findByRating(int rating);

    List<Company> findByCompanyNameAndRating(String name, int rating);

    List<CompanyDTO> findAll();
}
