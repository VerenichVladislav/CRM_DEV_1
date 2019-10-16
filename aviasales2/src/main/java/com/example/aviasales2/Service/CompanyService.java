package com.example.aviasales2.Service;

import com.example.aviasales2.Entity.Company;

public interface CompanyService {
    Company save(Company company);

    Company findByCompanyName(String name);

    String  deleteByCompanyId(long id);
}
