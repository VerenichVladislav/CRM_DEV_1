package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.CompanyFilter;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.QCompany;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.service.CompanyService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    @Transactional
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
    public List <Company> findAll(CompanyFilter companyFilter, Integer pageNo, Integer pageSize, String sortBy) {
        final QCompany qCompany = QCompany.company;
        BooleanBuilder booleanBuilder = new BooleanBuilder(qCompany.isNotNull());
        if (companyFilter.getCompanyName() != null) {
            booleanBuilder.and(qCompany.companyName.eq(companyFilter.getCompanyName()));
        }
        if (companyFilter.getRating() != 0) {
            booleanBuilder.and(qCompany.rating.eq(companyFilter.getRating()));
        }
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page <Company> pagedResult = companyRepository.findAll(booleanBuilder, paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList <>();
        }
    }


}
