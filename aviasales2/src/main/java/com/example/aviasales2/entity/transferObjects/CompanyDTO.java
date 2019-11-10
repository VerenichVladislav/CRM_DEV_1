package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CompanyDTO {
    private long companyId;

    private String companyName;

    private int rating;

    private int transportCount;
    private List<CommentsDTO> comments;

    private Set<TourDTO> tours;

    private Set<TransportDTO> transport;

    public CompanyDTO() {
    }
}
