package com.bachelor.microservice1.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gym gym;

    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private String description;
    private Long price;
    private Integer durationInDays;
    private Integer validityInDays;
    private LocalDateTime startDate = LocalDateTime.now();
    private LocalDateTime endOfOffer = LocalDateTime.now().plusDays(60);
    private Boolean isRegularOffer = false;

    public Offer() {
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
        return LocalDateTime.now().isBefore(this.endOfOffer);
    }


    public LocalDateTime getEndOfOffer() {
        return endOfOffer;
    }

    public void setEndOfOffer(LocalDateTime endOfOffer) {
        this.endOfOffer = endOfOffer;
    }

    public Boolean getRegularOffer() {
        return isRegularOffer;
    }

    public void setRegularOffer(Boolean regularOffer) {
        isRegularOffer = regularOffer;
    }
}
