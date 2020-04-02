package com.bachelor.microservice1.repository;

import com.bachelor.microservice1.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffersRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByGymId(Long gymId);
    Optional<Offer> findByNameAndAndDurationInDaysAndPriceAndGymId(String name, Integer durationInDays, Long price, Long gymId);
}
