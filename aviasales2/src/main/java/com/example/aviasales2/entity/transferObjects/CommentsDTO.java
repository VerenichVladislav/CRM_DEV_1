package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsDTO {
    private Long commentId;

    private String text;
    private String type;
    private String user;
    private long tour;
    private long company;
    private long hotel;

    public CommentsDTO() {
    }

}
