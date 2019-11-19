package com.example.aviasales2.config;

import com.example.aviasales2.entity.*;
import com.example.aviasales2.entity.transferObjects.*;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.dozer.loader.api.FieldsMappingOptions.*;


@Configuration
public class MappingConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder(){
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Transport.class, TransportDTO.class)
                        .fields("company.companyID", "company");
                mapping(Trip.class, TripDTO.class)
                        .fields("cityFrom.cityName", "cityFrom")
                        .fields("cityDest.cityName", "cityDest")
                        .fields("transport.name", "transport");
                mapping(City.class, CityDTO.class);
                mapping(Comments.class, CommentsDTO.class)
                        .fields("company.companyId", "company")
                        .fields("tour.id", "tour")
                        .fields("hotel.hotelId", "hotel");
                mapping(Company.class, CompanyDTO.class)
                .fields("transportId", "transport");
                mapping(Hotel.class, HotelDTO.class);

                mapping(Room.class, RoomDTO.class)
                        .fields("hotel.hotelId", "hotel");

                mapping(Tour.class, TourDTO.class)
                .fields("hotel.hotelId", "hotel")
                .fields("cityId.id", "cityId")
                .fields("company.companyId", "company");
                mapping(User.class, UserDTO.class)
                        .fields("wallet.id", "wallet");
                mapping(Wallet.class, WalletDTO.class)
                        .fields("owner.id", "owner");
                mapping(Ticket.class, TicketDTO.class)
                        .fields("cityFrom.id", "cityFrom")
                        .fields("cityDest.id", "cityDest")
                        .fields("buyer.id", "buyer");

            }
        };
    }

    @Bean
    public DozerBeanMapper dozerBeanMapper(){
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }
}
