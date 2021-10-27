package com.sda.project.repository;

import com.sda.project.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    Optional<Pet> findByName(String name);


    // TODO: step 3

}
