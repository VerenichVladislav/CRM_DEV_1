package com.example.aviasales2.service;

import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired
    TransportRepository transportRepository;

    @Override public Transport save(Transport transport) {
        return transportRepository.save(transport);
    }



    @Override
    public Optional<Transport> findById(Long id) {
        return transportRepository.findById(id);
    }
    @Override
    public List<Transport> findAll() {
        return transportRepository.findAll();
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