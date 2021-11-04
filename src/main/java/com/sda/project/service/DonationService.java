package com.sda.project.service;

import com.sda.project.controller.exception.ResourceNotFoundException;
import com.sda.project.dto.DonationAdd;
import com.sda.project.mapper.DonationMapper;
import com.sda.project.model.Donation;
import com.sda.project.model.User;
import com.sda.project.repository.DonationRepository;
import com.sda.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DonationService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private final DonationMapper donationMapper;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    @Autowired
    public DonationService(DonationMapper donationMapper,
                           DonationRepository donationRepository,
                           UserRepository userRepository) {
        this.donationMapper = donationMapper;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }

    public void save(DonationAdd donationAdd) {

        donationRepository.save(donationMapper.mapToDonation(donationAdd));
    }

    public List<DonationAdd> findAll() {
        return donationRepository.findAll()
                .stream().map(donation -> donationMapper
                        .mapToDonationAddDto(donation)).collect(Collectors.toList());
    }

    public Set<DonationAdd> findDonationByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        return user.getDonations().stream()
                .map(donation -> donationMapper.mapToDonationAddDto(donation))
                .collect(Collectors.toSet());
    }


    public DonationAdd ceva() {
        // find current user id
        Long currentUserId = 1L;

        // set current user id on dto
        return new DonationAdd(currentUserId, null, null);
    }
}
