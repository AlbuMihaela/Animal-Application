package com.sda.project.service;

import com.sda.project.dto.DonationAdd;
import com.sda.project.mapper.DonationMapper;
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

    private static final Logger log = LoggerFactory.getLogger(DonationService.class);

    private final DonationMapper donationMapper;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public DonationService(DonationMapper donationMapper,
                           DonationRepository donationRepository,
                           UserRepository userRepository,
                           UserService userService) {
        this.donationMapper = donationMapper;
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void save(DonationAdd donationAdd) {
// - find id
        Long loggedUserId = userService.findLoggedUserId();
        donationAdd.setUserId(loggedUserId);
        donationRepository.save(donationMapper.mapToDonation(donationAdd));
    }

    public List<DonationAdd> findAll() {
        return donationRepository.findAll()
                .stream().map(donation -> donationMapper
                        .mapToDonationAddDto(donation)).collect(Collectors.toList());
    }
//TODO use service
    public Set<DonationAdd> findDonationsByUserId(Long userId) {
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
