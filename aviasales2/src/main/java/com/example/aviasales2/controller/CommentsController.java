package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.repository.TourRepository;
import com.example.aviasales2.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private TourRepository tourRepository;
    @PostMapping("/save/{comp_id}/{hot_id}/{tour_id}")
    private Comments save(@PathVariable("comp_id")long comp_id, @PathVariable("hot_id")long hot_id, @PathVariable("tour_id") long tour_id, @RequestBody Comments comments){
        if(comp_id > 0){
            Company company = companyRepository.findByCompanyId(comp_id);
            comments.setCompany(company);
            company.getComments().add(comments);
            return commentsService.save(comments);
        }
        if (hot_id>0){
            Hotel hotel = hotelRepository.findByHotelId(hot_id);
            comments.setHotel(hotel);
            hotel.getComments().add(comments);
            return commentsService.save(comments);
        }
        if (tour_id > 0){
            Optional<Tour> tour = tourRepository.findById(tour_id);
            comments.setTour(tour);
            tour.get().getComments().add(comments);
            return commentsService.save(comments);
        }
        return commentsService.save(comments);

    }
}
