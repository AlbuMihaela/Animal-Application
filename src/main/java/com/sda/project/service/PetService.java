package com.sda.project.service;

import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.dto.PetDto;
import com.sda.project.dto.PetInfo;
import com.sda.project.mapper.PetMapper;
import com.sda.project.model.Category;
import com.sda.project.model.Pet;
import com.sda.project.model.User;
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
    private final UserService userService;

    @Autowired
    public PetService(PetRepository petRepository, PetMapper petMapper, UserService userService) {
        this.petRepository = petRepository;
        this.petMapper = petMapper;
        this.userService = userService;
    }

    public PetDto save(PetDto petDto) {
        petDto.setAvailable(true);
        Pet pet = petMapper.map(petDto);
        Pet savedPet = petRepository.save(pet);
        PetDto savedDto = petMapper.map(savedPet);
        return savedDto;
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

    public PetDto findById(Long id) {
        log.info("finding pet with id {}", id);
        return petRepository.findById(id)
                .map(pet -> petMapper.map(pet))
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }

    public List<PetInfo> getUserPets() {
        log.info("get user pets");
        User user = userService.findLoggedUser();
        return user.getAdoptions().stream()
                .map(adoption -> adoption.getPet())
                .map(pet -> petMapper.mapFromPetToPetInfo(pet))
                .collect(Collectors.toList());
    }

    public List<PetDto> getAvailablePets() {
        log.info("get available pets");
        return findAll().stream()
                .filter(petDto -> petDto.isAvailable())
                .collect(Collectors.toList());
    }

    public void update(PetDto dto) {
        log.info("updating pet with id {} with data {}", dto.getId(), dto);
        petRepository.findById(dto.getId())
                .map(pet -> petMapper.update(pet, dto))
                .map(updatedPet -> petRepository.save(updatedPet))
                .orElseThrow(() -> new ResourceNotFoundException("pet not found"));
    }

    public Pet update2(PetDto dto) {
        Pet petToUpdate = petRepository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("pet not found");
                });
        petToUpdate.setPhoto(dto.getPhoto());
        petToUpdate.setName(dto.getName());
        petToUpdate.setDescription(dto.getDescription());
        petToUpdate.setAvailable(dto.isAvailable());
        return petRepository.save(petToUpdate);
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}