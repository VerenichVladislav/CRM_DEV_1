package com.example.aviasales2.Service;

import com.example.aviasales2.Entity.Company;
import com.example.aviasales2.Repository.CompanyRepository;
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
    public String delete(long id){companyRepository.delete(companyRepository.findByCompanyId(id)); return "deleted";}

    @Override
    public Company findByCompanyName(String name){return companyRepository.findByCompanyName(name);}

    @Override
    public List<Company> findAllByTransportId(long id){return companyRepository.findAllByTransportId(id);}

    @Override
    public List<Company> findByRating(int rating){return companyRepository.findByRating(rating);}


}
