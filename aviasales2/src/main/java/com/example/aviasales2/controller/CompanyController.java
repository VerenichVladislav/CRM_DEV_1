package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.transferObjects.CompanyDTO;
import com.example.aviasales2.service.CompanyService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    public CompanyService companyService;
    @Autowired
    private DozerBeanMapper mapper;

    @GetMapping()
    public List<CompanyDTO> getAllCompany(){return companyService.findAll().stream().map(entity -> mapper.map(entity, CompanyDTO.class)).collect(Collectors.toList());}

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable(name = "id") long id){
        if(id > 0 && companyService.findByCompanyId(id) != null){
        return companyService.findByCompanyId(id);}
        else {return null;}

    }

    @GetMapping("/")
    public List<Company> getCompanyByNameAndRating(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long rating){
        if(name != null && rating != null) {
            return companyService.findByCompanyNameAndRating(name, Math.toIntExact(rating));
        } else if(name != null) {
            return Collections.singletonList(companyService.findByCompanyName(name));
        } else if(rating != null) {
            return companyService.findByRating(Math.toIntExact(rating));
        }
        return null;
    }


    @PostMapping
    public Company saveCompany(@RequestBody Company company){return companyService.save(company);}


    @PutMapping
    public String updateCompany(@RequestBody Company updatedCompany){
        Company company = companyService.findByCompanyId(updatedCompany.getCompanyId());
        if (company != null){
        companyService.save(updatedCompany);
        return "Updated";}
        return "Company does not exist!";
    }

    @DeleteMapping("/{id}")
    public String  deleteCompany(@PathVariable("id") long id){
        Company company = companyService.findByCompanyId(id);
        if (company!=null){
            companyService.delete(id);
            return "deleted";
        }
        else {
            return "Company does not exist!";
        }
    }
}
