package com.sda.project.service;

import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.dto.AdoptionDto;
import com.sda.project.mapper.AdoptionMapper;
import com.sda.project.mapper.PetMapper;
import com.sda.project.model.Pet;
import com.sda.project.repository.AdoptionRepository;
import com.sda.project.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionService {

    private static final Logger log = LoggerFactory.getLogger(AdoptionService.class);

    private final PetService petService;
    private final PetMapper petMapper;
    private final PetRepository petRepository;
    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;
    private final UserService userService;

    @Autowired
    public AdoptionService(PetService petService, PetMapper petMapper, PetRepository petRepository, AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper, UserService userService) {
        this.petService = petService;
        this.petMapper = petMapper;
        this.petRepository = petRepository;
        this.adoptionRepository = adoptionRepository;
        this.adoptionMapper = adoptionMapper;
        this.userService = userService;
    }

    public void save(AdoptionDto adoptionDto) {
//        adoptionDto.getPet().setAvailable(false);
//        Pet petToUpdate = petRepository.findByNameIgnoreCase(adoptionDto.getPet().getName())
//                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
//        petToUpdate.setAvailable(false);
//        petService.update2(petMapper.map(adoptionDto.getPet()));
        adoptionDto.setUser(userService.findLoggedUser());
        //update User

        adoptionRepository.save(adoptionMapper.map(adoptionDto));
    }

    public List<AdoptionDto> findAll() {
        return adoptionRepository.findAll()
                .stream().map(adoption -> adoptionMapper.map(adoption))
                .collect(Collectors.toList());
    }
}
