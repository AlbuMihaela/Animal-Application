package com.sda.project.service;

import com.sda.project.model.Pet;
import com.sda.project.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    public PetRepository petRepository;

    // TODO: step 2
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

}
