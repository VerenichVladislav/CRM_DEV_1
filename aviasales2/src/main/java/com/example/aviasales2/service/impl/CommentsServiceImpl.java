package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Comment;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.repository.TourRepository;
import com.example.aviasales2.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;
    private final HotelRepository hotelRepository;
    private final CompanyRepository companyRepository;
    private final TourRepository tourRepository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository, HotelRepository hotelRepository, CompanyRepository companyRepository, TourRepository tourRepository) {
        this.commentsRepository = commentsRepository;
        this.hotelRepository = hotelRepository;
        this.companyRepository = companyRepository;
        this.tourRepository = tourRepository;
    }

    @Transactional
    @Override
    public void save(Comment comment) {
        commentsRepository.save(comment);
        updateCommentRating(comment);
    }

    @Override
    public List <Comment> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public Comment findCommentsById(Long id) {
        return commentsRepository.findByCommentId(id);
    }

    @Override
    public void deleteById(Long id) {
        List <Comment> comments;
        Comment newComment = commentsRepository.findByCommentId(id);
        BigDecimal curRate;
        if (newComment.getHotel() == null) {
            if (newComment.getCompany() == null) {
                curRate = newComment.getTour().getCommentRating();
                comments = commentsRepository.findByTour(newComment.getTour());
            } else {
                curRate = newComment.getCompany().getCommentRating();
                comments = commentsRepository.findByCompany(newComment.getCompany());
            }
        } else {
            curRate = newComment.getHotel().getCommentRating();
            comments = commentsRepository.findByHotel(newComment.getHotel());
        }
        if (comments.size() == 1) {
            if (newComment.getHotel() == null) {
                if (newComment.getCompany() == null) {
                    tourRepository.findByTourId(newComment.getTour().getTourId()).setCommentRating(BigDecimal.valueOf(0));
                    tourRepository.save(tourRepository.findByTourId(newComment.getTour().getTourId()));
                } else {
                    companyRepository.findByCompanyId(newComment.getCompany().getCompanyId()).setCommentRating(BigDecimal.valueOf(0));
                    companyRepository.save(companyRepository.findByCompanyId(newComment.getCompany().getCompanyId()));
                }
            } else {
                hotelRepository.findByHotelId(newComment.getHotel().getHotelId()).setCommentRating(BigDecimal.valueOf(0));
                hotelRepository.save(hotelRepository.findByHotelId(newComment.getHotel().getHotelId()));
            }
            commentsRepository.deleteByCommentId(id);
        } else {
            BigDecimal sumRate = curRate.multiply(BigDecimal.valueOf(comments.size()));
            sumRate = sumRate.setScale(0, RoundingMode.HALF_UP);
            sumRate = sumRate.subtract(BigDecimal.valueOf(newComment.getRate()));
            BigDecimal commentRate = sumRate.divide(BigDecimal.valueOf(comments.size() - 1), 2, RoundingMode.HALF_DOWN);

            if (newComment.getHotel() == null) {
                if (newComment.getCompany() == null) {
                    tourRepository.findByTourId(newComment.getTour().getTourId()).setCommentRating(commentRate);
                    tourRepository.save(tourRepository.findByTourId(newComment.getTour().getTourId()));
                } else {
                    companyRepository.findByCompanyId(newComment.getCompany().getCompanyId()).setCommentRating(commentRate);
                    companyRepository.save(companyRepository.findByCompanyId(newComment.getCompany().getCompanyId()));
                }
            } else {
                hotelRepository.findByHotelId(newComment.getHotel().getHotelId()).setCommentRating(commentRate);
                hotelRepository.save(hotelRepository.findByHotelId(newComment.getHotel().getHotelId()));
            }
        }
        commentsRepository.deleteByCommentId(id);
    }

    @Override
    public List <Comment> findByCompanyId(Long id) {
        return commentsRepository.findByCompany(companyRepository.findByCompanyId(id));
    }

    @Override
    public List <Comment> findByTourId(Long id) {
        return commentsRepository.findByTour(tourRepository.findByTourId(id));
    }

    @Override
    public List <Comment> findByHotelId(Long id) {
        return commentsRepository.findByHotel(hotelRepository.findByHotelId(id));
    }

    @Override
    public BigDecimal getTourRate(Long id) {
        return tourRepository.findByTourId(id).getCommentRating();
    }

    @Override
    public BigDecimal getCompanyRate(Long id) {
        return companyRepository.findByCompanyId(id).getCommentRating();
    }

    @Override
    public BigDecimal getHotelRate(Long id) {
        return hotelRepository.findByHotelId(id).getCommentRating();
    }

    private BigDecimal getSumRate(List <Comment> comments) {
        BigDecimal sumRate = new BigDecimal(0);
        for (Comment comment : comments)
            sumRate = sumRate.add(BigDecimal.valueOf(comment.rate));

        sumRate = sumRate.divide(BigDecimal.valueOf(comments.size()), 2, RoundingMode.HALF_DOWN);
        return sumRate;
    }

    @Transactional
    public void updateCommentRating(Comment newComment) {
        List <Comment> comments;
        BigDecimal sumRate;
        if (newComment.getHotel() == null) {
            if (newComment.getCompany() == null) {
                comments = commentsRepository.findByTour(newComment.getTour());
                sumRate = getSumRate(comments);
                Tour tour = tourRepository.findByTourId(newComment.getTour().getTourId());
                tour.setCommentRating(sumRate);
                tourRepository.save(tour);
            } else {
                comments = commentsRepository.findByCompany(newComment.getCompany());
                sumRate = getSumRate(comments);
                Company company = companyRepository.findByCompanyId(newComment.getCompany().getCompanyId());
                company.setCommentRating(sumRate);
                companyRepository.save(company);
            }
        } else {
            comments = commentsRepository.findByHotel(newComment.getHotel());
            sumRate = getSumRate(comments);
            Hotel hotel = hotelRepository.findByHotelId(newComment.getHotel().getHotelId());
            hotel.setCommentRating(sumRate);
            hotelRepository.save(hotel);
        }
    }
}