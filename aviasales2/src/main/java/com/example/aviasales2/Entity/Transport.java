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
public class Transport {
    @Id
    @GeneratedValue
   public long id;
    String name;
    int baggage;
    public Transport(){

    }
    public Long getId(){
        return this.id;
    }
}