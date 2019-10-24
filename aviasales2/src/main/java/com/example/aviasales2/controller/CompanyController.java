package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/{id:\\d+}")
    private Company getCompanyById(@PathVariable(name = "id") long id){return companyService.findByCompanyId(id);}

    @PostMapping("/save")
    private Company saveCompany(@RequestBody Company company){return companyService.save(company);}

    @GetMapping("/delete")
    private
    String deleteCompany(@RequestParam("id") long id){return companyService.delete(id);}

    @GetMapping("/getByName")
    private Company getCompanyByName(@RequestParam(name = "name")String name){return companyService.findByCompanyName(name);}

    @PostMapping("/update")
    private Company updateCompany(@RequestBody Company updatedCompany){return companyService.save(updatedCompany);}

    @GetMapping("/getByRating")
    private List<Company> getCompanyByRating(@RequestParam("rating")int rating){return companyService.findByRating(rating);}

    @GetMapping("/getAll")
    private List<Company> getAllCompany(){return companyService.findAll();}


}
