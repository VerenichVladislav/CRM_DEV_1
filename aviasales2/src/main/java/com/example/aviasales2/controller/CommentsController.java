package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.entity.transferObjects.CommentsDTO;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.service.HotelService;
import com.example.aviasales2.service.TourService;
import com.example.aviasales2.service.impl.CommentsServiceImpl;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@RestController
@RequestMapping("/comments")
public class CommentsController {
    private final CommentsServiceImpl commentsServiceImpl;
    private final TourService tourService;
    private final CompanyService companyService;
    private final HotelService hotelService;
    private final DozerBeanMapper mapper;

    @Autowired
    public CommentsController(CommentsServiceImpl commentsServiceImpl, TourService tourService, CompanyService companyService, HotelService hotelService, DozerBeanMapper mapper) {
        this.commentsServiceImpl = commentsServiceImpl;
        this.tourService = tourService;
        this.companyService = companyService;
        this.hotelService = hotelService;
        this.mapper = mapper;
    }

    @Transactional
    @PostMapping
    public void save(@RequestBody Comments comments) {
        commentsServiceImpl.save(comments);
    }

    @GetMapping
    public List <CommentsDTO> findAll() {
        return commentsServiceImpl.findAll().stream().map(entity -> mapper.map(entity, CommentsDTO.class)).collect(Collectors.toList());
    }

    @PutMapping("/tour/{tourId}")
    public String updateTourComment(@PathVariable("tourId") long tourId, @RequestBody Comments comments) {
        Comments old = commentsServiceImpl.findCommentsById(comments.getCommentId());
        if (old != null) {
            Tour tour = tourService.findByTourId(tourId);
            comments.setTour(tour);
            tour.getComments().add(comments);
            commentsServiceImpl.save(comments);
            return "Updated";
        }
        return "Error!";
    }

    @PutMapping("/company/{companyId}")
    public String updateCompanyComment(@PathVariable("companyId") long companyId, @RequestBody Comments comments) {
        Comments old = commentsServiceImpl.findCommentsById(comments.getCommentId());
        if (old != null) {
            Company company = companyService.findByCompanyId(companyId);
            comments.setCompany(company);
            company.getComments().add(comments);
            commentsServiceImpl.save(comments);
            return "Updated";
        }
        return "Error!";
    }

    @PutMapping("/hotel/{hotelId}")
    public String updateHotelComment(@PathVariable("hotelId") long hotelId, @RequestBody Comments comments) {
        Comments old = commentsServiceImpl.findCommentsById(comments.getCommentId());
        if (old != null) {
            Hotel hotel = hotelService.findByHotelId(hotelId);
            comments.setHotel(hotel);
            hotel.getComments().add(comments);
            commentsServiceImpl.save(comments);
            return "Updated";
        }
        return "Error!";
    }

    @GetMapping("/hotel/{hotelId}")
    public List <CommentsDTO> findCommentsByHotelId(@PathVariable("hotelId") Long id) {
        return commentsServiceImpl.findByHotelId(id).stream().map(entity -> mapper.map(entity, CommentsDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/company/{companyId}")
    public List <CommentsDTO> findCommentsByCompanyId(@PathVariable("companyId") Long id) {
        return commentsServiceImpl.findByCompanyId(id).stream().map(entity -> mapper.map(entity, CommentsDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/tour/{tourId}")
    public List <CommentsDTO> findCommentsByTourId(@PathVariable("tourId") Long id) {
        return commentsServiceImpl.findByTourId(id).stream().map(entity -> mapper.map(entity, CommentsDTO.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        commentsServiceImpl.deleteById(id);
    }

    @GetMapping("/hotel/rate/{hotelid}")
    public BigDecimal getHotelRate(@PathVariable("hotelid") Long id) {
        return commentsServiceImpl.getHotelRate(id);
    }

    @GetMapping("/company/rate/{companyId}")
    public BigDecimal getCompanyRate(@PathVariable("companyId") Long id) {
        return commentsServiceImpl.getCompanyRate(id);
    }

    @GetMapping("/tour/rate/{tourId}")
    public BigDecimal getTourRate(@PathVariable("tourId") Long id) {
        return commentsServiceImpl.getTourRate(id);
    }
}
