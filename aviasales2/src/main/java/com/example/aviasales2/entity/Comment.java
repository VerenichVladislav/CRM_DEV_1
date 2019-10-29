package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    long commentId;
    String text;
    String type;
    long tourId;
    long hotelId;
    long companyId;

    public Comment() {
    }

    public long getCommentId() {
        return commentId;
    }
}
