package com.example.aviasales2.entity.transferObjects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long userId;

    @Email(message = "Bad email")
    @NotBlank(message = "Email should be not null")
    private String email;
    private String currency;
    @NotBlank(message = "Role must be not null")
    private String role;
    @NotBlank(message = "User name should be not null")
    @Size(min = 4, max = 15, message = "Bad user name length")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Bad user name")
    private String userName;
    @NotBlank(message = "First name should be not null")
    @Size(max = 15, message = "Bad first name length")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Bad first name")
    private String firstName;
    @NotBlank(message = "Last name should be not null")
    @Size(max = 15, message = "Bad last name length")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "Bad last name")
    private String lastName;
    @NotBlank(message = "Password should be not null")
    @Size(min = 4, max = 30, message = "Bad password length")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Bad password")
    private String hashPass;
    private String state = "UnConfirmed";
    private String confirmingHash;
    private boolean isLocked;
    @Null
    private Long wallet;
    private List <TicketDTO> tickets;
    private List <ReservationDTO> reservations;
    private List <TourDTO> tours;

    public UserDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConfirmingHash() {
        return confirmingHash;
    }

    public void setConfirmingHash(String confirmingHash) {
        this.confirmingHash = confirmingHash;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Long getWallet() {
        return wallet;
    }

    public void setWallet(Long wallet) {
        this.wallet = wallet;
    }

    public List <TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List <TicketDTO> tickets) {
        this.tickets = tickets;
    }

    public List <ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List <ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public List <TourDTO> getTours() {
        return tours;
    }

    public void setTours(List <TourDTO> tours) {
        this.tours = tours;
    }
}
