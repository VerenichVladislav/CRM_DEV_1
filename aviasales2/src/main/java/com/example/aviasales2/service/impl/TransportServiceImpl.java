package com.example.aviasales2.service.impl;

import com.example.aviasales2.config.filterConfig.TransportFilter;
import com.example.aviasales2.entity.QTransport;
import com.example.aviasales2.entity.Transport;
import com.example.aviasales2.entity.transferObjects.TransportDTO;
import com.example.aviasales2.repository.TransportRepository;
import com.example.aviasales2.service.TransportService;
import com.querydsl.core.BooleanBuilder;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {
    private final TransportRepository transportRepository;
    private final DozerBeanMapper mapper;

    @Autowired
    public TransportServiceImpl(TransportRepository transportRepository, DozerBeanMapper mapper) {
        this.transportRepository = transportRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Transport save(Transport transport) {
        return transportRepository.save(transport);
    }

    @Override
    public Transport findByName(String name) {
        return transportRepository.findByName(name);
    }

    @Override
    public Optional <Transport> findById(Long id) {
        return transportRepository.findById(id);
    }

    @Override
    public List <Transport> findAll(TransportFilter transportFilter, Integer pageNo, Integer pageSize, String sortBy) {
        final QTransport qTransport = QTransport.transport;
        BooleanBuilder booleanBuilder = new BooleanBuilder(qTransport.isNotNull());
        if (transportFilter.getName() != null) {
            booleanBuilder.and(qTransport.name.eq(transportFilter.getName()));
        }
        if (transportFilter.getPassengerCapacity() != null) {
            booleanBuilder.and(qTransport.passengerCapacity.eq(Integer.valueOf(transportFilter.getPassengerCapacity())));
        }
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page <Transport> pagedResult = transportRepository.findAll(booleanBuilder, paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList <>();
        }
    }

    @Transactional
    @Override
    public Transport update(TransportDTO transport) {
        if (transportRepository.existsById(transport.getTransportId())) {
            return transportRepository.save(mapper.map(transport, Transport.class));
        }
        return null;
    }

    @Override
    public ResponseEntity <String> getCityName(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transportRepository.findByTransportId(id).getName());
    }

    @Override
    public void deleteById(Long id) {
        transportRepository.deleteById(id);
    }


}
