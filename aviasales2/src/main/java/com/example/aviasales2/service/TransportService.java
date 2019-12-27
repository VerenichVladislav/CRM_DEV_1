package com.example.aviasales2.service;

import com.example.aviasales2.config.filterConfig.TransportFilter;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.transferObjects.TransportDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TransportService {
    Transport save(Transport transport);

    Transport findByName(String name);

    Optional <Transport> findById(Long id);

    List <Transport> findAll(TransportFilter transportFilter, Integer pageNo, Integer pageSize, String sortBy);

    Transport update(TransportDTO transport);

    void deleteById(Long id);

    ResponseEntity <String> getCityName(Long id);
}
