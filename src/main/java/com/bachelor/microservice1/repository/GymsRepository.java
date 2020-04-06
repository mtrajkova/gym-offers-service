package com.bachelor.microservice1.repository;

import com.bachelor.microservice1.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GymsRepository extends JpaRepository<Gym, Long> {
    Optional<Gym> findById(Long id);

    Optional<Gym> findByName(String name);

    List<Gym> findAllByIdIn(List<Long> gymIds);
}
