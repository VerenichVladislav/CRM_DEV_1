package com.example.aviasales2.controller;

import com.example.aviasales2.config.filterConfig.TransportFilter;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.transferObjects.TransportDTO;
import com.example.aviasales2.exception.GlobalBadRequestException;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.service.TransportService;
import com.example.aviasales2.util.TransportValidator;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RequestMapping("/transports")
@RestController
public class TransportController {
    private final TransportService transportService;
    private final CompanyService companyService;
    private final DozerBeanMapper mapper;
    private final TransportValidator transportValidator;

    @Autowired
    public TransportController(TransportService transportService, CompanyService companyService, DozerBeanMapper mapper, TransportValidator transportValidator) {
        this.transportService = transportService;
        this.companyService = companyService;
        this.mapper = mapper;
        this.transportValidator = transportValidator;
    }

    @PostMapping("/filter")
    public List <TransportDTO> getAllTransport(@RequestBody TransportFilter transportFilter,
                                               @RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "name") String sortBy) {
        return transportService.findAll(transportFilter, pageNo, pageSize, sortBy).stream()
                .map(entity -> mapper.map(entity, TransportDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public TransportDTO findByCompanyName(@RequestParam String name) {
        return mapper.map(transportService.findByName(name), TransportDTO.class);
    }

    @GetMapping("/{id}")
    public TransportDTO getTransportById(@PathVariable("id") Long id) {
        return mapper.map(transportService.findById(id), TransportDTO.class);
    }

    @PutMapping
    public TransportDTO updateTransport(@RequestBody @Valid TransportDTO transportDTO, BindingResult result) {
        transportValidator.validate(transportDTO, result);
        if (result.hasErrors()) {
            return null;
        }
        return mapper.map(transportService.update(transportDTO), TransportDTO.class);
    }


    @DeleteMapping("/{id}")
    public void deleteTransport(@PathVariable("id") Long id) {
        transportService.deleteById(id);
    }


    @PostMapping
    public TransportDTO save(@RequestBody @Valid TransportDTO transportDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new GlobalBadRequestException(result);
        }
        Company company = companyService.findByCompanyId(transportDto.getCompany());
        Transport transport = mapper.map(transportDto, Transport.class);
        transport.setCompany(company);
        company.getTransportId().add(transport);
        company.setTransportCount(company.getTransportCount() + 1);
        return mapper.map(transportService.save(transport), TransportDTO.class);
    }

    @PostMapping("/company/{companyId}")
    public TransportDTO saveCompanyId(@PathVariable(name = "companyId") long companyId, @RequestBody @Valid TransportDTO transportDto, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        Company company = companyService.findByCompanyId(companyId);
        Transport transport = mapper.map(transportDto, Transport.class);
        transport.setCompany(company);
        company.getTransportId().add(transport);
        company.setTransportCount(company.getTransportCount() + 1);
        return mapper.map(transportService.save(transport), TransportDTO.class);
    }

    @GetMapping("/name/{id}")
    public ResponseEntity <String> getTransportName(@PathVariable Long id) {
        return transportService.getCityName(id);
    }
}
