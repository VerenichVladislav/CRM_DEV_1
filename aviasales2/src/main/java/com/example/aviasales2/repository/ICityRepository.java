package com.example.aviasales2.repository;

import com.example.aviasales2.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICityRepository extends CrudRepository <City, Long> {

    City findByCityId(Long id);

    City findByCityName(String cityName);

    List <City> findAll();

    void deleteByCityName(String cityName);

    void deleteById(Long id);


}