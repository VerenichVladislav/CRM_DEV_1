package com.example.aviasales2.repository;

import com.example.aviasales2.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
    Comment findByCommentId(long id);

    void deleteById(long id);

    List<Comment> findByTourId(long tourId);

    List<Comment> findByHotelId(long hotelId);

    List<Comment> findByCompanyId(long companyId);

    List<Comment> findAll();
}
