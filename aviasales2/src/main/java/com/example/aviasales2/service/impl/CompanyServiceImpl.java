package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.transferObjects.CompanyDTO;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.service.CompanyService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DozerBeanMapper mapper;

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
    public List<Company> findByCompanyNameAndRating(String name, int rating) {
        return companyRepository.findByCompanyNameAndRating(name, rating);
    }

    @Override
    public List<CompanyDTO> findAll(){return companyRepository.findAll().stream().map(entity -> mapper.map(entity, CompanyDTO.class)).collect(Collectors.toList());}


}
