package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CompanyDTO {
    private Long companyId;
    private BigDecimal commentRating;
    @NotBlank(message = "company name is null")
    @Size(max = 25)
    private String companyName;

    @Pattern(regexp = "[0-5]", message = "{invalid.rating}")
    private String rating;

    private int transportCount;
    private List <CommentsDTO> comments;

    private Set <TourDTO> tours;

    private Set <TransportDTO> transport;

    public CompanyDTO() {
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTransportCount() {
        return transportCount;
    }

    public void setTransportCount(int transportCount) {
        this.transportCount = transportCount;
    }

    public List <CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List <CommentsDTO> comments) {
        this.comments = comments;
    }

    public Set <TourDTO> getTours() {
        return tours;
    }

    public void setTours(Set <TourDTO> tours) {
        this.tours = tours;
    }

    public Set <TransportDTO> getTransport() {
        return transport;
    }

    public void setTransport(Set <TransportDTO> transport) {
        this.transport = transport;
    }
}
