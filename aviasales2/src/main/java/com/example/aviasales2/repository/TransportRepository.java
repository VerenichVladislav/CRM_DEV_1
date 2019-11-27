package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Transport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends CrudRepository<Transport,Long> {
    Transport findByTransportId(Long id);
    List<Transport> findAll();
    Transport deleteAllByName(String name);
    Transport findByName(String name);
}
