package com.example.aviasales2.service;

import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.transferObjects.TransportDTO;

import java.util.List;
import java.util.Optional;

public interface TransportService {
    Transport save(Transport transport);

    Optional <Transport> findById(Long id);

    List <Transport> findAll();

    Transport update(TransportDTO transport);

    void deleteById(Long id);
}
