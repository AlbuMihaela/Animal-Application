package com.sda.project.repository;

import com.sda.project.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    // TODO: step 3

}
