package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.transferObjects.CompanyDTO;
import com.example.aviasales2.exception.GlobalBadRequestException;
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
    public ResponseEntity<Object> saveCompany(@RequestBody @Valid Company company, BindingResult result) throws GlobalBadRequestException {
        companyValidator.validate(company, result);
        if(result.hasErrors()) {
            throw new GlobalBadRequestException(result);
        }
        companyService.save(company);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @PutMapping
    public Company updateCompany(@RequestBody @Valid CompanyDTO updatedCompany, BindingResult result){
        if (companyService.findByCompanyId(updatedCompany.getCompanyId()) != null) {
            companyValidator.updateValidate(updatedCompany, result);
            if (result.hasErrors()) {
                return null;
            }

            return companyService.save(mapper.map(updatedCompany, Company.class));
        }
     return null;
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
