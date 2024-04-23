package com.martynov.clickerAppBackend.domain.repositories;

import com.martynov.clickerAppBackend.domain.entities.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
    Optional<Sneaker> findById(Long id);

    void deleteById(Long id);

    List<Sneaker> findAll();
}
