package com.example.aviasales2.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal sum;

    @OneToOne(mappedBy = "wallet")
    private User owner;

    public Wallet(){

    }
}
