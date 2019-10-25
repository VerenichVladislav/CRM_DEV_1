package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/{id}")
    private Company getCompanyById(@PathVariable(name = "id") long id){
        if(id>0 && companyService.findByCompanyId(id) != null){
        return companyService.findByCompanyId(id);}
        else {return null;}

    }

    @PostMapping("/save")
    private Company saveCompany(@RequestBody Company company){return companyService.save(company);}

    @GetMapping("/delete/{id}")
    private String  deleteCompany(@PathVariable(name = "id") long id){
        Company company = companyRepository.findByCompanyId(id);
        if (company!=null){
        companyService.delete(id);
        return "deleted";
        }
        else {
            return "Company does not exist!";
        }
    }

    @GetMapping("/getByName")
    private Company getCompanyByName(@RequestParam(name = "name")String name){return companyService.findByCompanyName(name);}

    @PutMapping("/update")
    private String updateCompany(@RequestBody Company updatedCompany){
        Company company = companyService.findByCompanyId(updatedCompany.getCompanyId());
        if (company != null){
        companyService.save(updatedCompany);
        return "Updated";}
        return "Company does not exist!";
    }

    @GetMapping("/getByRating")
    private List<Company> getCompanyByRating(@RequestParam("rating")int rating){return companyService.findByRating(rating);}

    @GetMapping("/getAll")
    private List<Company> getAllCompany(){return companyService.findAll();}


}
