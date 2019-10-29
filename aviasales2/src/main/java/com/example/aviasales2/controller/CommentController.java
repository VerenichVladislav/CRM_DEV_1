package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Comment;
import com.example.aviasales2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/getById")
    private Comment getCommentById(@RequestParam("id")long id){return commentService.findByCommentId(id);}

    @PostMapping("/save")
    private Comment saveComment(@RequestBody Comment comment){return commentService.save(comment);}

    @GetMapping("/delete")
    private void deleteComment(@RequestParam("id") long id){commentService.deleteById(id);}

    @GetMapping("/getByTourId")
    private List<Comment> getCommentByTourId(@RequestParam("id") long id){return commentService.findByTourId(id);}

    @PostMapping("/update")
    private void updateComment(@RequestBody Comment updatedComment){commentService.update(updatedComment);}

    @GetMapping("/getByHotelId")
    private List<Comment> getCommentByHotelId(@RequestParam("id") long id){return commentService.findByHotelId(id);}

    @GetMapping("/getByCompanyId")
    private List<Comment> getCommentByCompanyId(@RequestParam("id") long id){return commentService.findByCompanyId(id);}

    @GetMapping("/getAll")
    private List<Comment> getAllComment(){return commentService.findAll();}


}
