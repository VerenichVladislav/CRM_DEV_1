package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsDTO {
    private long id;

    private String text;
    private String type;
    private long tour;
    private long company;
    private long hotel;

    public CommentsDTO() {
    }

}
