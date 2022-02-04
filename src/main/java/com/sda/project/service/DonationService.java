package com.sda.project.service;

import com.sda.project.dto.AddDonation;
import com.sda.project.dto.DonationInfo;
import com.sda.project.mapper.DonationMapper;
import com.sda.project.model.Donation;
import com.sda.project.model.Product;
import com.sda.project.model.User;
import com.sda.project.repository.DonationRepository;
import com.sda.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

    public void save(AddDonation addDonation) {
        User loggedUser = userService.findLoggedUser();
        Donation donationToSave = donationMapper.mapToDonation(addDonation, loggedUser);
        if (donationToSave.getProduct().equals(Product.MONEY)) {
            donationToSave.setDetails("transfer");
        }
        donationRepository.save(donationToSave);
    }

    public List<AddDonation> findAll() {
        return donationRepository.findAll()
                .stream().map(donation -> donationMapper
                        .mapToDonationAddDto(donation)).collect(Collectors.toList());
    }

    public Set<DonationInfo> findDonationByUser(User user) {
        return donationRepository.findAll().stream()
                .filter(donation -> donation.getUser().equals(user))
                .map(donation -> donationMapper.mapFromDonationToDonationInfo(donation))
                .collect(Collectors.toSet());
    }

}
