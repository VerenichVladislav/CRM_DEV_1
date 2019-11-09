package com.example.aviasales2.service;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.transferObjects.CommentsDTO;

import java.util.List;

public interface CommentsService {
    Comments save(Comments comments);
    List<CommentsDTO> findAll();
    Comments findCommentsById(long id);
    String deleteById(long id);
}
