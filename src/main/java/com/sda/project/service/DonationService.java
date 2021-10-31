package com.sda.project.service;

import com.sda.project.dto.DonationDto;
import com.sda.project.mapper.DonationMapper;
import com.sda.project.repository.DonationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private final DonationMapper donationMapper;
    private final DonationRepository donationRepository;

    @Autowired
    public DonationService(DonationMapper donationMapper, DonationRepository donationRepository) {
        this.donationMapper = donationMapper;
        this.donationRepository = donationRepository;
    }

    public void save(DonationDto donationDto) {
        donationRepository.save(donationMapper.map(donationDto));
    }

    public List<DonationDto> findAll() {
        return donationRepository.findAll()
                .stream().map(donation -> donationMapper
                        .map(donation)).collect(Collectors.toList());
    }
}
