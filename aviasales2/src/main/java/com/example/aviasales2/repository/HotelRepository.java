package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Hotel;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long>, QuerydslPredicateExecutor<Hotel> {
    Hotel findByHotelName(String hotelName);
    Hotel findByHotelId(long id);
    String findImageByHotelId(long id);
}
