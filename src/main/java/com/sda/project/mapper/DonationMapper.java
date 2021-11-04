package com.sda.project.mapper;

import com.sda.project.dto.DonationAdd;
import com.sda.project.dto.DonationInfo;
import com.sda.project.model.Donation;
import com.sda.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper {

    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public DonationMapper(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    public Donation mapToDonation(DonationAdd donationAddDto) {
        Donation entity = new Donation();
        entity.setUser(donationAddDto.getUserId());
        entity.setProduct(donationAddDto.getProduct());
        entity.setDetails(donationAddDto.getDetails());
        return entity;
    }

    public DonationAdd mapToDonationAddDto(Donation entity) {
        DonationAdd donationAddDto = new DonationAdd();
        donationAddDto.setUserId(entity.getUser().getId());
        donationAddDto.setProduct(entity.getProduct());
        donationAddDto.setDetails(entity.getDetails());
        return donationAddDto;
    }

    public Donation mapToDonation(DonationInfo donationInfoDto) {
        Donation entity = new Donation();
        entity.setUser(userMapper.mapToUser(donationInfoDto.getUser()));
        entity.setProduct(donationInfoDto.getProduct());
        entity.setDetails(donationInfoDto.getDetails());
        return entity;
    }
}
