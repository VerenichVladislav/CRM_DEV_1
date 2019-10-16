package com.example.aviasales2.Repository;

import com.example.aviasales2.Entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    Company findByCompanyName(String name);

    Company deleteByCompanyId(Long id);

}
