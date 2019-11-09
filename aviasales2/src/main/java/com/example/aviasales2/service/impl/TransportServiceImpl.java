package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.transferObjects.TransportDTO;
import com.example.aviasales2.repository.TransportRepository;
import com.example.aviasales2.service.TransportService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired
    TransportRepository transportRepository;
    @Autowired
    DozerBeanMapper mapper;

    @Override public Transport save(Transport transport) {
        return transportRepository.save(transport);
    }



    @Override
    public Optional<Transport> findById(Long id) {
        return transportRepository.findById(id);
    }
    @Override
    public List<TransportDTO> findAll() {
        return transportRepository.findAll().stream()
                .map(entity -> mapper.map(entity, TransportDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Transport transport) {
        if(transportRepository.existsById(transport.getId())){
            transportRepository.save(transport);}
    }

    @Override
    public void deleteById(Long id) {
        transportRepository.deleteById(id);
    }


}