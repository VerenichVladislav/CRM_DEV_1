package com.example.aviasales2.controller;

import com.example.aviasales2.config.filterConfig.CompanyFilter;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.transferObjects.CompanyDTO;
import com.example.aviasales2.exception.GlobalBadRequestException;
import com.example.aviasales2.exception.NoSuchEntityException;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.util.CompanyValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final DozerBeanMapper mapper;
    private final CompanyValidator companyValidator;

    @Autowired
    public CompanyController(CompanyService companyService, DozerBeanMapper mapper, CompanyValidator companyValidator) {
        this.companyService = companyService;
        this.mapper = mapper;
        this.companyValidator = companyValidator;
    }

    @PostMapping("/filter")
    public List <CompanyDTO> getAllCompany(@RequestBody CompanyFilter companyFilter,
                                           @RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "companyName") String sortBy) {
        return companyService.findAll(companyFilter, pageNo, pageSize, sortBy)
                .stream()
                .map(entity -> mapper.map(entity, CompanyDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CompanyDTO getCompanyById(@PathVariable(name = "id") long id) {
        if (id > 0 && companyService.findByCompanyId(id) != null) {
            return mapper.map(companyService.findByCompanyId(id), CompanyDTO.class);
        } else {
            return null;
        }

    }

    @GetMapping("/")
    public CompanyDTO findByCompanyName(@RequestParam String name) {
        return mapper.map(companyService.findByCompanyName(name), CompanyDTO.class);
    }

    @PostMapping
    public ResponseEntity <CompanyDTO> saveCompany(@RequestBody @Valid CompanyDTO company, BindingResult result) throws GlobalBadRequestException {
        companyValidator.validate(company, result);
        if (result.hasErrors()) {
            throw new GlobalBadRequestException(result);
        }
        CompanyDTO body = mapper.map(companyService.save(mapper.map(company, Company.class)), CompanyDTO.class);
        return new ResponseEntity <>(body, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity <CompanyDTO> updateCompany(@RequestBody @Valid CompanyDTO updatedCompany, BindingResult result) {
        if (companyService.findByCompanyId(updatedCompany.getCompanyId()) != null) {
            companyValidator.updateValidate(updatedCompany, result);
            if (result.hasErrors()) {
                throw new GlobalBadRequestException(result);
            }
            CompanyDTO body = mapper.map(companyService.save(mapper.map(updatedCompany, Company.class)), CompanyDTO.class);
            return new ResponseEntity <>(body, HttpStatus.OK);
        }
        throw new NoSuchEntityException(Company.class);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") long id) {
        Company company = companyService.findByCompanyId(id);
        if (company != null) {
            companyService.delete(id);
        } else {
            throw new NoSuchEntityException(Company.class);
        }
    }

    @Transactional
    @DeleteMapping("/")
    public void deleteByCompanyName(@RequestParam String companyName) {
        Company company = companyService.findByCompanyName(companyName);
        if (company != null) {
            companyService.deleteByCompanyName(companyName);
        } else {
            throw new NoSuchEntityException(Company.class);
        }

    }
}
