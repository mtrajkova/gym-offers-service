package com.bachelor.microservice1.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gym gym;

    private String name;
    private String description;
    private Long price;
    private Integer durationInDays;
    private Integer validityInDays;
    private LocalDateTime startDate;
    private LocalDateTime endOfOffer;
    private Boolean isOfferValid;

    public Offer() {
        this.startDate = LocalDateTime.now();
        this.isOfferValid = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(Integer durationInDays) {
        this.durationInDays = durationInDays;
    }

    public Integer getValidityInDays() {
        return validityInDays;
    }

    public void setValidityInDays(Integer validityInDays) {
        this.validityInDays = validityInDays;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Boolean isOfferValid() {
        return isOfferValid;
    }


    public LocalDateTime getEndOfOffer() {
        return endOfOffer;
    }

    public void setEndOfOffer(LocalDateTime endOfOffer) {
        this.endOfOffer = endOfOffer;
    }
}
