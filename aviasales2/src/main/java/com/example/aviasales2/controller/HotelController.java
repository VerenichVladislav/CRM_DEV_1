package com.example.aviasales2.controller;

import com.example.aviasales2.entity.Comments;
import com.example.aviasales2.entity.Hotel;
import com.example.aviasales2.entity.Tour;
import com.example.aviasales2.repository.CommentsRepository;
import com.example.aviasales2.service.CommentsService;
import com.example.aviasales2.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/{id:\\d+}")
    public Hotel getHotelById(@PathVariable("id") long id) {
        return hotelService.findByHotelId(id);
    }

    @PostMapping("/hotel_comment/{hotel_id}")
    public Comments saveComment(@PathVariable("hotel_id") long hotel_id,@RequestBody Comments comment){
        Hotel hotel = hotelService.findByHotelId(hotel_id);
        hotel.getComments().add(comment);
        comment.setHotel(hotel);
        return commentsService.save(comment);
    }

    @GetMapping("/name/{name}")
    public Hotel getHotelByName(
            @PathVariable("name") String hotelName) {
        return hotelService.findByHotelName(hotelName);
    }

    @GetMapping
    public List<Hotel> getAllHotel() {
        return hotelService.findAll();
    }

    @PostMapping()
    public Hotel saveHotel(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }

    @PutMapping()
    public Hotel updateHotel(@RequestBody Hotel hotel) {
        Hotel old = hotelService.findByHotelId(hotel.getHotelId());
        if(old != null)
            return hotelService.save(hotel);
        return null;
    }

    @PutMapping("/update_comment/{comment_id}")
    private String update(@PathVariable("comment_id") long comment_id, @RequestBody Comments comments){
        comments.setId(comment_id);
        Comments old = commentsRepository.findCommentsById(comments.getId());
        if(old!=null){
            Hotel hotel = old.getHotel();
            old.setText(comments.getText());
            old.setHotel(hotel);
            commentsService.save(old);
            return "Updated";}
        return "Error!";
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteHotel(@PathVariable("id") long id){
        hotelService.deleteById(id);
    }

}
