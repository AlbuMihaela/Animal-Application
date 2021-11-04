package com.sda.project.mapper;

import com.sda.project.dto.DonationAdd;
import com.sda.project.dto.DonationInfo;
import com.sda.project.dto.UserInfoDto;
import com.sda.project.model.Donation;
import com.sda.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DonationMapper {

    private final UserMapper userMapper;

    @Autowired
    public DonationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Donation mapToDonation(DonationAdd donationAddDto) {
        Donation entity = new Donation();
        entity.setProduct(donationAddDto.getProduct());
        entity.setDetails(donationAddDto.getDetails());
        return entity;
    }

    public DonationAdd mapToDonationAddDto(Donation entity) {
        DonationAdd donationAddDto = new DonationAdd();
//        donationAddDto.setUserId(entity.getUser().getId());
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
