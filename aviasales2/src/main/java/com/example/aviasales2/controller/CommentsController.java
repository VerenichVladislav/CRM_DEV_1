package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CommentsRepository commentsRepository;
    @PostMapping("/save")
    private Comments save(@RequestBody Comments comments){
        return commentsService.save(comments);
    }
    @GetMapping
    private List<Comments> findAll(){return commentsService.findAll();}
    @DeleteMapping("/delete/{id}")
    private String delete(@PathVariable("id") long id){return commentsService.deleteById(id);}

}
