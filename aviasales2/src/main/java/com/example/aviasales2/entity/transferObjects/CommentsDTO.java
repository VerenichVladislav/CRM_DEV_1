package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentsDTO {
    public byte rate;
    private Long commentId;
    private Timestamp date;
    private String text;
    private String type;
    private String user;
    private long tour;
    private long company;
    private long hotel;

    public CommentsDTO() {
    }

}
