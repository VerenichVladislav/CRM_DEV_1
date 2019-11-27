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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourServiceImpl implements TourService {
    @Autowired
    TourRepository tourRepository;
    @Autowired
    DozerBeanMapper mapper;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ICityRepository cityRepository;

    @Override
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
    public List<Tour> findAll() {
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

    @Override
    public Tour update(TourDTO tour) {

        return tourRepository.save(mapper.map(tour, Tour.class));
    }

    @Override
    public Tour findByTourId(Long id) {
        return tourRepository.findByTourId(id);
    }
}
