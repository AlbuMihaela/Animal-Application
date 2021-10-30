package com.sda.project.service;

import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.dto.PetDto;
import com.sda.project.mapper.PetMapper;
import com.sda.project.model.Pet;
import com.sda.project.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    private static final Logger log = LoggerFactory.getLogger(PetService.class);

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

    public List<PetDto> findAll() {
        return petRepository.findAll().stream()
                .map(pet -> petMapper.map(pet))
                .collect(Collectors.toList());
    }

    public Pet findByName(String name) {
        return petRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }


    public List<Pet> findByCategory(String category) {
        return petRepository.findByCategory(category).get();
    }


//    public void update(String name) {
//        Pet pet = petRepository.findByNameIgnoreCase(name)
//                .orElseThrow(() -> {
//                    throw new ResourceNotFoundException("the pet " + name + " does not exist.");
//                });
//        PetDto petDto = petMapper.map(pet);
//        save(petDto);
//    }

    public void delete(PetDto petDto) {
        petRepository.delete(petMapper.map(petDto));
    }
}
