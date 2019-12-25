package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Comment;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository <Comment, Long> {
    List <Comment> findAll();

    void deleteByCommentId(Long id);

    Comment findByCommentId(Long id);

    List <Comment> findByHotel(Hotel hotel);

    List <Comment> findByTour(Tour tour);

    List <Comment> findByCompany(Company company);
}
