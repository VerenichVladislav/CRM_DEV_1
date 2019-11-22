package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.transferObjects.CommentsDTO;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.repository.CompanyRepository;
import com.example.aviasales2.repository.HotelRepository;
import com.example.aviasales2.repository.TourRepository;
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
    private HotelRepository hotelRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    TourRepository tourRepository;
    @Override
    public Comments save(Comments comments){return commentsRepository.save(comments);}
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

}
