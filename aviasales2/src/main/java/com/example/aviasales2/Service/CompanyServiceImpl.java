package com.example.aviasales2.Service;

import com.example.aviasales2.Entity.Company;
import com.example.aviasales2.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company save(Company company){return companyRepository.save(company);}

    @Override
    public Company findByCompanyName(String name){return companyRepository.findByCompanyName(name);}


    @Override
    public String deleteByCompanyId(long id){companyRepository.deleteByCompanyId(id); return "deleted";}


}
