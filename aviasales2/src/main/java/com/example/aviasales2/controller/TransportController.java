package com.example.aviasales2.controller;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.repository.TransportRepository;
import com.example.aviasales2.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RequestMapping("/Transport")
@RestController
public class TransportController {
    @Autowired
    TransportService transportService;

    @Autowired
    TransportRepository transportRepository;

    @Autowired
    CompanyRepository companyRepository;
    @GetMapping("/getAllTransport")
    private List<Transport> getAllTransport()
    {
        return transportService.findAll();
    }

    @GetMapping("/getTransport")
    private Optional<Transport> getTransport(@RequestParam(name = "id")Long id){
        return transportService.findById(id);
    }

    @PostMapping("/updateTransport")
    private void updateTransport(@RequestBody Transport transport)
    {
        transportService.update(transport);
    }



    @GetMapping("/deleteTransport")
    private void deleteTransport(@RequestParam(name = "id")Long id){
        transportService.deleteById(id);
    }


    @PostMapping("/save/{companyId}")
    private Transport save(@PathVariable(name = "companyId") long companyId, @RequestBody Transport transport) {
        Company company = companyRepository.findByCompanyId(companyId);
        transport.setCompany(company);
        company.getTransportId().add(transport);
        company.setTransportCount(company.getTransportCount() + 1);
        return transportService.save(transport);
    }




}