package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Transport;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends PagingAndSortingRepository <Transport, Long>, QuerydslPredicateExecutor <Transport> {
    Transport findByTransportId(Long id);

    List <Transport> findAll();

    Transport deleteAllByName(String name);

    Transport findByName(String name);
}
