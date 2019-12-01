package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.transferObjects.TransportDTO;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.service.TransportService;
import com.example.aviasales2.util.TransportValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/transports")
@RestController
public class TransportController {
    @Autowired
    private TransportService transportService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DozerBeanMapper mapper;
    @Autowired
    private TransportValidator transportValidator;

    @GetMapping
    public List<TransportDTO> getAllTransport() {
        return transportService.findAll().stream()
                .map(entity -> mapper.map(entity, TransportDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransportDTO getTransportById(@PathVariable("id") Long id) {
        return mapper.map(transportService.findById(id), TransportDTO.class);
    }

    @PutMapping
    public TransportDTO updateTransport(@RequestBody @Valid TransportDTO transportDTO, BindingResult result) {
        transportValidator.validate(transportDTO, result);
        if (result.hasErrors())
        {return null;}
        return mapper.map(transportService.update(transportDTO), TransportDTO.class);
    }


    @DeleteMapping("/{id}")
    public void deleteTransport(@PathVariable("id") Long id) {
        transportService.deleteById(id);
    }


    @PostMapping("/company/{companyId}")
    public TransportDTO save(@PathVariable(name = "companyId") long companyId, @RequestBody @Valid TransportDTO transportDto, BindingResult result) {
        if(result.hasErrors())
        {
            return null;
        }
        Company company = companyService.findByCompanyId(companyId);
        Transport transport = mapper.map(transportDto, Transport.class);
        transport.setCompany(company);
        company.getTransportId().add(transport);
        company.setTransportCount(company.getTransportCount() + 1);
        return mapper.map(transportService.save(transport), TransportDTO.class);
    }


}
