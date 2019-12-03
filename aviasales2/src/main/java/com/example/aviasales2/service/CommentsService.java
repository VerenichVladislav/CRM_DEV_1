package com.example.aviasales2.service;

import com.example.aviasales2.entity.Comments;

import java.util.List;

public interface CommentsService {
    void save(Comments comments);

    List <Comments> findAll();

    Comments findCommentsById(Long id);

    void deleteById(Long id);

    List <Comments> findByHotelId(Long id);

    List <Comments> findByCompanyId(Long id);

    List <Comments> findByTourId(Long id);
}
