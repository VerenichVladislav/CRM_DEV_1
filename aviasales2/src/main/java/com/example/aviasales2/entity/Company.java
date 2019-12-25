package com.example.aviasales2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long companyId;
    @NotBlank(message = "company name is null")
    @Size(max = 25)
    private String companyName;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List <Comment> comments;

    @OneToMany(mappedBy = "company",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set <Tour> tours;

    private double rating;

    private BigDecimal commentRating = new BigDecimal(0);

    private int transportCount;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set <Transport> transportId;

    public Company() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public BigDecimal getCommentRating() {
        return commentRating;
    }

    public void setCommentRating(BigDecimal commentRating) {
        this.commentRating = commentRating;
    }
}
