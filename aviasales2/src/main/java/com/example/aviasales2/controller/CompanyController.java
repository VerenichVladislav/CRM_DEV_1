package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.transferObjects.CompanyDTO;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.util.CompanyValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
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
    @Autowired
    private CompanyValidator companyValidator;

    @GetMapping()
    public List<CompanyDTO> getAllCompany(){return companyService.findAll().stream().map(entity -> mapper.map(entity, CompanyDTO.class)).collect(Collectors.toList());}

    @GetMapping("/{id}")
    public CompanyDTO getCompanyById(@PathVariable(name = "id") long id){
        if(id > 0 && companyService.findByCompanyId(id) != null){
        return mapper.map(companyService.findByCompanyId(id),CompanyDTO.class);}
        else {return null;}

    }

    @GetMapping("/badRequest")
    public String badRequest(){
        return "Something goes wrong!";
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
    public String saveCompany(@RequestBody @Valid Company company, BindingResult result){
        companyValidator.validate(company, result);
        if(result.hasErrors()) {
            return String.valueOf(result.getFieldErrors());
        }
        companyService.save(company);
        return "saved!";}


    @PutMapping
    public String updateCompany(@RequestBody @Valid Company updatedCompany, BindingResult result){

        if(updatedCompany.getCompanyId() != null){
        companyValidator.validate(updatedCompany, result);
        if (result.hasErrors()){
            return String.valueOf(result.getFieldErrors());
        }
        companyService.save(updatedCompany);
        return "updated";}
        return "no such company";
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
