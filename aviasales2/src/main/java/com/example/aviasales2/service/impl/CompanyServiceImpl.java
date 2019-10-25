package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company save(Company company){return companyRepository.save(company);}

    @Override
    public Company findByCompanyId(long id){return companyRepository.findByCompanyId(id);}


    @Override
    public void delete(long id){ companyRepository.delete(companyRepository.findByCompanyId(id));}

    @Override
    public Company findByCompanyName(String name){return companyRepository.findByCompanyName(name);}

    @Override
    public List<Company> findByRating(int rating){return companyRepository.findByRating(rating);}

    @Override
    public List<Company> findAll(){return companyRepository.findAll();}


}
