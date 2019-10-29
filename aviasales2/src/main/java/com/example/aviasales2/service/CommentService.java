package com.example.aviasales2.service;

import com.example.aviasales2.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment tour);
    void update(Comment tour);
    List<Comment> findAll();
    Comment findByCommentId(long id);
    List<Comment> findByTourId(long id);
    List<Comment> findByHotelId(long id);
    List<Comment> findByCompanyId(long id);
    void deleteById(long id);
}
