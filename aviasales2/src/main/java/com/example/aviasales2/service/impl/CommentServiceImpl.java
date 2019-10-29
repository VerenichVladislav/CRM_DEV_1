package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Comment;
import com.example.aviasales2.repository.CommentRepository;
import com.example.aviasales2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findByTourId(long id) {
        return commentRepository.findByTourId(id);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findByHotelId(long id) {
        return commentRepository.findByHotelId(id);
    }

    @Override
    public List<Comment> findByCompanyId(long id) {
        return commentRepository.findByCompanyId(id);
    }

    @Override
    public Comment findByCommentId(long id) {
        return commentRepository.findByCommentId(id);
    }
}
