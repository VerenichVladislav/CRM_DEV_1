package com.example.aviasales2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue
    long companyId;

    String companyName;

    long transportId;

    long commentId;

    int rating;

    public Company(){}
}
