package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.transferObjects.CommentsDTO;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.service.CommentsService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    DozerBeanMapper mapper;
    @Override
    public Comments save(Comments comments){return commentsRepository.save(comments);}
    @Override
    public List<CommentsDTO> findAll(){return commentsRepository.findAll().stream().map(entity -> mapper.map(entity, CommentsDTO.class)).collect(Collectors.toList());}

    @Override
    public Comments findCommentsById(long id) {
        return commentsRepository.findCommentsById(id);
    }

    ;
    @Override
    public String deleteById(long id){commentsRepository.deleteById(id); return "Deleted";}

}
