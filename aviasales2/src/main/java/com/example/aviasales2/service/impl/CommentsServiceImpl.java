package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.entity.transferObjects.CommentsDTO;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.repository.TourRepository;
import com.example.aviasales2.service.CommentsService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    TourRepository tourRepository;

    @Override
    public void save(Comments comments){
        commentsRepository.save(comments);
        updateCommentRating(comments);
    }

    @Override
    public List<Comments> findAll(){return commentsRepository.findAll();}

    @Override
    public Comments findCommentsById(Long id) {
        return commentsRepository.findByCommentId(id);
    }

    @Override
    public void deleteById(Long id){commentsRepository.deleteByCommentId(id); }

    @Override
    public List <Comments> findByCompanyId(Long id) {
        return commentsRepository.findByCompany(companyRepository.findByCompanyId(id));
    }

    @Override
    public List <Comments> findByTourId(Long id) {
        return commentsRepository.findByTour(tourRepository.findByTourId(id));
    }

    @Override
    public List <Comments> findByHotelId(Long id) {
        return commentsRepository.findByHotel(hotelRepository.findByHotelId(id));
    }

    public BigDecimal getTourRate(Long id) {
        return tourRepository.findByTourId(id).getCommentRating();
    }

    public BigDecimal getCompanyRate(Long id) {
        return companyRepository.findByCompanyId(id).getCommentRating();
    }

    public BigDecimal getHotelRate(Long id) {
        return hotelRepository.findByHotelId(id).getCommentRating();
    }

    public void updateCommentRating(Comments newComment) {
        List <Comments> comments;
        if(newComment.getHotel() == null) {
            if (newComment.getCompany() == null) {
                comments = commentsRepository.findByTour(newComment.getTour());
            } else {
                comments = commentsRepository.findByCompany(newComment.getCompany());
            }
        }
        else {
            comments = commentsRepository.findByHotel(newComment.getHotel());
        }
        BigDecimal sumRate= new BigDecimal(0);
        int n =0;
        for (Comments comment: comments) {
            sumRate = sumRate.add(BigDecimal.valueOf(comment.rate));
            n++;
        }
        sumRate = sumRate.divide(BigDecimal.valueOf(n),2, RoundingMode.HALF_DOWN);
        if(newComment.getHotel() == null) {
            if (newComment.getCompany() == null) {
                Tour tour = tourRepository.findByTourId(newComment.getTour().getTourId());
                tour.setCommentRating(sumRate);
                tourRepository.save(tour);
            } else {
                Company company = companyRepository.findByCompanyId(newComment.getCompany().getCompanyId());
                company.setCommentRating(sumRate);
                companyRepository.save(company);
            }
        }
        else {
            Hotel hotel = hotelRepository.findByHotelId(newComment.getHotel().getHotelId());
            hotel.setCommentRating(sumRate);
            hotelRepository.save(hotel);
        }
    }
}