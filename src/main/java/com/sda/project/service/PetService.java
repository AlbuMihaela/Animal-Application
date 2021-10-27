package com.sda.project.service;

import com.sda.project.dto.PetDto;
import com.sda.project.mapper.PetMapper;
import com.sda.project.model.Pet;
import com.sda.project.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;

    public void save(PetDto petDto) {
        Pet pet = petMapper.map(petDto);
        petRepository.save(pet);
    }

    // TODO: step 2
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public List<Pet> findbyCategory(String category) {
        return petRepository.findAll().stream().filter(pet -> String.valueOf(pet.getCategory()).equals(category)).collect(Collectors.toList());
    }

}
