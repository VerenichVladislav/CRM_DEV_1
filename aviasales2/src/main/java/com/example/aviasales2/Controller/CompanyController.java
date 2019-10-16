package com.example.aviasales2.Controller;

import com.example.aviasales2.Entity.Company;
import com.example.aviasales2.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/getCompany")
    private Company getCompany(@RequestParam(name="name")String name){return companyService.findByCompanyName(name);}

    @PostMapping("/save")
    private Company saveCompany(@RequestBody Company company){return companyService.save(company);}

    @GetMapping("/delete")
    private String deleteCompany(@RequestParam("id") long id){return companyService.deleteByCompanyId(id);}

}
