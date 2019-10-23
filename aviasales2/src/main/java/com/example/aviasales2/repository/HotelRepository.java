package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    Hotel findByHotelName(String hotelName);
    Hotel findByHotelId(long id);
}
