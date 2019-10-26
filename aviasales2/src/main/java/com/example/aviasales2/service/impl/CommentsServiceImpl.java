package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Override
    public Comments save(Comments comments){return commentsRepository.save(comments);}
    @Override
    public List<Comments> findAll(){return commentsRepository.findAll();};
    @Override
    public String deleteById(long id){commentsRepository.deleteById(id); return "Deleted";}

}
