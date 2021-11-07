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

    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;

    @Autowired
    public AdoptionService(AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper) {
        this.adoptionRepository = adoptionRepository;
        this.adoptionMapper = adoptionMapper;
    }

    public void save(AdoptionDto adoptionDto) {
        adoptionRepository.save(adoptionMapper.map(adoptionDto));
    }

    public List<AdoptionDto> findAll() {
        return adoptionRepository.findAll()
                .stream().map(adoption -> adoptionMapper.map(adoption))
                .collect(Collectors.toList());
    }
}
