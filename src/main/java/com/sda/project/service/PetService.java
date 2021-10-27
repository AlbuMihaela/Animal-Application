package com.sda.project.service;

import com.sda.project.dto.PetDto;
import com.sda.project.mapper.PetMapper;
import com.sda.project.model.Pet;
import com.sda.project.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Autowired
    public PetService(PetRepository petRepository, PetMapper petMapper) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
    }

    public void save(PetDto petDto) {
        Pet pet = petMapper.map(petDto);
        petRepository.save(pet);
    }

    // TODO: step 2
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    // TODO: remove dead code (that doesn't work)
    public List<Pet> findByCategory(String category) {
//        return petRepository.findAll().stream().filter(pet -> String.valueOf(pet.getCategory()).equals(category)).collect(Collectors.toList());
        return petRepository.findByCategory(category).get();
    }
}
