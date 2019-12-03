package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository <Comments, Long> {
    List <Comments> findAll();

    Comments deleteByCommentId(Long id);

    Comments findByCommentId(Long id);

    List <Comments> findByHotel(Hotel hotel);

    List <Comments> findByTour(Tour tour);

    List <Comments> findByCompany(Company company);
}
