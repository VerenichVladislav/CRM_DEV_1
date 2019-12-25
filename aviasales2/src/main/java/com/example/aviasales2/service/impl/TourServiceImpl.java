package com.example.aviasales2.service.impl;

import com.example.aviasales2.entity.City;
import com.example.aviasales2.entity.Company;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.entity.transferObjects.TourDTO;
import com.example.aviasales2.repository.ICityRepository;
import com.example.aviasales2.repository.TourRepository;
import com.example.aviasales2.service.CompanyService;
import com.example.aviasales2.service.HotelService;
import com.example.aviasales2.service.TourService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final DozerBeanMapper mapper;
    private final CompanyService companyService;
    private final HotelService hotelService;
    private final ICityRepository cityRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, DozerBeanMapper mapper, CompanyService companyService, HotelService hotelService, ICityRepository cityRepository) {
        this.tourRepository = tourRepository;
        this.mapper = mapper;
        this.companyService = companyService;
        this.hotelService = hotelService;
        this.cityRepository = cityRepository;
    }

    @Override
    @Transactional
    public Tour save(TourDTO tourDTO, long cityId, long companyId, long hotelId) {

        Company company = companyService.findByCompanyId(companyId);
        City city = cityRepository.findByCityId(cityId);
        Tour tour = mapper.map(tourDTO, Tour.class);
        tour.setHotel(hotelService.findByHotelId(hotelId));
        tour.setCityId(cityRepository.findByCityId(cityId));
        tour.setCompany(companyService.findByCompanyId(companyId));
        company.getTours().add(tour);
        city.getTours().add(tour);
        return tourRepository.save(tour);
    }

    @Override
    public List <Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public Tour findByName(String name) {
        return tourRepository.findByName(name);
    }

    @Override
    public Tour deleteById(Long id) {
        return tourRepository.deleteByTourId(id);
    }

    @Transactional
    @Override
    public Tour update(TourDTO tour) {
        return tourRepository.save(mapper.map(tour, Tour.class));
    }

    @Override
    public Tour findByTourId(Long id) {
        return tourRepository.findByTourId(id);
    }
}
