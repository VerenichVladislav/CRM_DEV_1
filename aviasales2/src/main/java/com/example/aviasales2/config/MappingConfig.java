package com.example.aviasales2.config;

import com.example.aviasales2.entity.*;
import com.example.aviasales2.entity.transferObjects.*;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MappingConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Transport.class, TransportDTO.class)
                        .fields("company.companyId", "company");
                mapping(Trip.class, TripDTO.class)
                        .fields("cityFrom.cityId", "cityFrom")
                        .fields("cityDest.cityId", "cityDest")
                        .fields("transport.transportId", "transport");
                mapping(Trip.class, SearchResultTripDto.class)
                        .fields("cityFrom.cityName", "cityFrom")
                        .fields("cityDest.cityName", "cityDest")
                        .fields("transport.name", "transport");
                mapping(City.class, CityDTO.class);
                mapping(Comment.class, CommentsDTO.class)
                        .fields("company.companyId", "company")
                        .fields("tour.tourId", "tour")
                        .fields("user.userName", "user")
                        .fields("hotel.hotelId", "hotel");
                mapping(Company.class, CompanyDTO.class)
                        .fields("transportId", "transport");
                mapping(Hotel.class, HotelDTO.class)
                        .fields("city.cityId", "cityId");

                mapping(Room.class, RoomDTO.class)
                        .fields("hotel.hotelId", "hotel");
                mapping(Reservation.class, ReservationDTO.class)
                        .fields("buyer.userId", "buyer")
                        .fields("hotel.hotelId", "hotel");

                mapping(Tour.class, TourDTO.class)
                        .fields("hotel.hotelId", "hotel")
                        .fields("cityId.cityId", "cityId")
                        .fields("company.companyId", "company");
                mapping(User.class, UserDTO.class)
                        .fields("wallet.walletId", "wallet");
                mapping(Wallet.class, WalletDTO.class)
                        .fields("owner.userId", "owner");
                mapping(Ticket.class, TicketDTO.class)
                        .fields("cityFrom.cityId", "cityFrom")
                        .fields("cityDest.cityId", "cityDest")
                        .fields("buyer.userId", "buyer");

            }
        };
    }

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }
}
