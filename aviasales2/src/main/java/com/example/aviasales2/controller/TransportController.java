package com.example.aviasales2.controller;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/transports")
@RestController
public class TransportController {
    @Autowired
    private TransportService transportService;

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Transport> getAllTransport()
    {
        return transportService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Transport> getTransportById(@PathVariable("id") Long id){
        return transportService.findById(id);
    }

    @PutMapping
    public void updateTransport(@RequestBody Transport transport)
    {
        transportService.update(transport);
    }


    @DeleteMapping("/{id}")
    public void deleteTransport(@PathVariable("id") Long id){
        transportService.deleteById(id);
    }


    @PostMapping("/company/{companyId}")
    public Transport save(@PathVariable(name = "companyId") long companyId, @RequestBody Transport transport) {
        Company company = companyService.findByCompanyId(companyId);
        transport.setCompany(company);
        company.getTransportId().add(transport);
        company.setTransportCount(company.getTransportCount() + 1);
        return transportService.save(transport);
    }




}