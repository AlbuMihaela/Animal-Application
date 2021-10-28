package com.sda.project.repository;

import com.sda.project.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    Optional<Pet> findByNameIgnoreCase(String name);

    Optional<List<Pet>> findByCategory(String category);


    // TODO: step 3

}
