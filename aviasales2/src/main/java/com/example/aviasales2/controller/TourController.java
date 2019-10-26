package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.service.CommentsService;
import com.example.aviasales2.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/id/{id}")
    public Tour getTourById(@PathVariable long id) {
        return tourService.findByTourId(id);
    }

    @GetMapping("/name/{name}")
    public Tour getTourByTourName(@PathVariable String name) {
        return tourService.findByName(name);
    }

    @GetMapping("/All")
    public List <Tour> findAll() {
        return tourService.findAll();
    }

    @PostMapping("/toyr_comment/{tour_id}")
    public Comments saveComment(@PathVariable("tour_id") long tour_id, @RequestBody Comments comment){
        Tour tour = tourService.findByTourId(tour_id);
        tour.getComments().add(comment);
        comment.setTour(tour);
        return commentsService.save(comment);
    }

    @PutMapping("/update_comment/{comment_id}")
    private String update(@PathVariable("comment_id") long comment_id, @RequestBody Comments comments){
        comments.setId(comment_id);
        Comments old = commentsRepository.findCommentsById(comments.getId());
        if(old!=null){
            Tour tour = old.getTour();
            old.setText(comments.getText());
            old.setTour(tour);
            commentsService.save(old);
            return "Updated";}
        return "Error!";
    }

    @DeleteMapping("/delete/{id}")
    public Tour deleteTour(@PathVariable(name = "id") long id) {
        return tourService.deleteById(id);
    }

    @PostMapping("/save")
    public Tour save(@RequestBody Tour tour) {
        return tourService.save(tour);
    }

    @PostMapping("/update")
    private void update(@RequestBody Tour newTour) {
        tourService.save(newTour);
    }
}

