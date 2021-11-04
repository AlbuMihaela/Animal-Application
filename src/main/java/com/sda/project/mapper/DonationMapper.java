package com.sda.project.mapper;

import com.sda.project.dto.AddDonation;
import com.sda.project.dto.DonationInfo;
import com.sda.project.model.Donation;
import com.sda.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper {

    private final UserMapper userMapper;

    @Autowired
    public DonationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Donation mapToDonation(AddDonation addDonationDto, User user) {
        Donation entity = new Donation();
        entity.setUser(user);
        entity.setProduct(addDonationDto.getProduct());
        entity.setDetails(addDonationDto.getDetails());
        return entity;
    }

    public AddDonation mapToDonationAddDto(Donation entity) {
        AddDonation addDonationDto = new AddDonation();
        addDonationDto.setUserId(entity.getUser().getId());
        addDonationDto.setProduct(entity.getProduct());
        addDonationDto.setDetails(entity.getDetails());
        return addDonationDto;
    }

    public Donation mapToDonation(DonationInfo donationInfoDto) {
        Donation entity = new Donation();
        entity.setUser(userMapper.mapToUser(donationInfoDto.getUser()));
        entity.setProduct(donationInfoDto.getProduct());
        entity.setDetails(donationInfoDto.getDetails());
        return entity;
    }
}
