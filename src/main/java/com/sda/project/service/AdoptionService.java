package com.sda.project.service;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.mapper.AdoptionMapper;
import com.sda.project.model.Adoption;
import com.sda.project.model.Pet;
import com.sda.project.model.User;
import com.sda.project.repository.AdoptionRepository;
import com.sda.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionService {

    private static final Logger log = LoggerFactory.getLogger(AdoptionService.class);

    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AdoptionService(AdoptionRepository adoptionRepository,
                           AdoptionMapper adoptionMapper,
                           UserService userService,
                           UserRepository userRepository) {
        this.adoptionRepository = adoptionRepository;
        this.adoptionMapper = adoptionMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Transactional
    public void save(AdoptionDto adoptionDto) {
        log.info("save adoption {}", adoptionDto);

        Pet pet = adoptionDto.getPet();
        pet.setAvailable(false);

        Adoption adoption = adoptionMapper.toEntity(adoptionDto);
        adoption.setPet(pet);

        User loggedUser = userService.findLoggedUser();
        loggedUser.addAdoption(adoption);
        userRepository.save(loggedUser);
        adoptionRepository.save(adoption);
    }

    public List<AdoptionDto> findAll() {
        return adoptionRepository.findAll()
                .stream()
                .map(adoption -> adoptionMapper.toDto(adoption))
                .collect(Collectors.toList());
    }
}
