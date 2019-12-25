package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Company;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository <Company, Long>, QuerydslPredicateExecutor <Company> {
    Company findByCompanyId(long id);

    void deleteByCompanyId(Long id);

    void deleteByCompanyName(String name);

    Company findByCompanyName(String name);

    List <Company> findByRating(int rating);

    List <Company> findByCompanyNameAndRating(String name, int rating);

    List <Company> findAll();
}
