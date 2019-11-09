package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.entity.transferObjects.CommentsDTO;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.service.CommentsService;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.service.HotelService;
import com.example.aviasales2.service.TourService;
import com.example.aviasales2.service.impl.CommentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsServiceImpl commentsServiceImpl;
    @Autowired
    private TourService tourService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public Comments save(@RequestBody Comments comments){
        return commentsServiceImpl.save(comments);
    }

    @GetMapping
    public List<CommentsDTO> findAll(){return commentsServiceImpl.findAll();}

    @PutMapping("/tour/{tourId}")
    public String updateTourComment(@PathVariable("tourId") long tourId, @RequestBody Comments comments){
        Comments old = commentsServiceImpl.findCommentsById(comments.getId());
        if(old != null){
            Tour tour = tourService.findByTourId(tourId);
            comments.setTour(tour);
            tour.getComments().add(comments);
            commentsServiceImpl.save(comments);
            return "Updated";
        }
        return "Error!";
    }

    @PutMapping("/company/{companyId}")
    public String updateCompanyComment(@PathVariable("companyId") long companyId, @RequestBody Comments comments){
        Comments old = commentsServiceImpl.findCommentsById(comments.getId());
        if(old != null){
            Company company = companyService.findByCompanyId(companyId);
            comments.setCompany(company);
            company.getComments().add(comments);
            commentsServiceImpl.save(comments);
            return "Updated";
        }
        return "Error!";
    }

    @PutMapping("/hotel/{hotelId}")
    public String updateHotelComment(@PathVariable("hotelId") long hotelId, @RequestBody Comments comments){
        Comments old = commentsServiceImpl.findCommentsById(comments.getId());
        if(old != null){
            Hotel hotel = hotelService.findByHotelId(hotelId);
            comments.setHotel(hotel);
            hotel.getComments().add(comments);
            commentsServiceImpl.save(comments);
            return "Updated";
        }
        return "Error!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){return commentsServiceImpl.deleteById(id);}

}
