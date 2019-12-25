package com.example.aviasales2.service;

import com.example.aviasales2.entity.Comment;

import java.math.BigDecimal;
import java.util.List;

public interface CommentsService {
    void save(Comment comment);

    List <Comment> findAll();

    Comment findCommentsById(Long id);

    void deleteById(Long id);

    List <Comment> findByHotelId(Long id);

    List <Comment> findByCompanyId(Long id);

    List <Comment> findByTourId(Long id);

    BigDecimal getTourRate(Long id);

    BigDecimal getCompanyRate(Long id);

    BigDecimal getHotelRate(Long id);

    void updateCommentRating(Comment newComment);
}
