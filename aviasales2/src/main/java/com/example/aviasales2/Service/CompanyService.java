package com.example.aviasales2.Service;

import com.example.aviasales2.Entity.Company;

import java.util.List;

public interface CompanyService {
    Company save(Company company);

    Company findByCompanyId(long id);

    String delete(long id);

    Company findByCompanyName(String name);

    List<Company> findAllByTransportId(long id);

    List<Company> findByRating(int rating);


}
