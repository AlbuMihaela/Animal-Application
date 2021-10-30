package com.sda.project.service;

import com.sda.project.dto.AdoptionDto;
import com.sda.project.mapper.AdoptionMapper;
import com.sda.project.repository.AdoptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private final AdoptionMapper adoptionMapper;

    private final AdoptionRepository adoptionRepository;

    @Autowired
    public AdoptionService(AdoptionMapper adoptionMapper, AdoptionRepository adoptionRepository) {
        this.adoptionMapper = adoptionMapper;
        this.adoptionRepository = adoptionRepository;
    }

    public void save(AdoptionDto adoptionDto) {

        adoptionRepository.save(adoptionMapper.map(adoptionDto));

    }

}
