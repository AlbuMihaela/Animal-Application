package com.sda.project.service;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.mapper.AdoptionMapper;
import com.sda.project.repository.AdoptionRepository;
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
    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;
    private final UserService userService;

    @Autowired
    public AdoptionService(PetService petService, AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper, UserService userService) {
        this.petService = petService;
        this.adoptionRepository = adoptionRepository;
        this.adoptionMapper = adoptionMapper;
        this.userService = userService;
    }

    public void save(AdoptionDto adoptionDto) {
        adoptionDto.getPetDto().setAvailable(false);
        petService.update(adoptionDto.getPetDto());
        userService.updateByUserId(adoptionDto.getUserId());
        //update User
        adoptionRepository.save(adoptionMapper.map(adoptionDto));
    }

    public List<AdoptionDto> findAll() {
        return adoptionRepository.findAll()
                .stream().map(adoption -> adoptionMapper.map(adoption))
                .collect(Collectors.toList());
    }
}
