package com.sda.project.mapper;

import com.sda.project.dto.DonationDto;
import com.sda.project.model.Donation;
import com.sda.project.model.Product;
import org.springframework.stereotype.Service;

@Service
public class DonationMapper {

    public Donation map(DonationDto donationDto){
        Donation donation = new Donation();
        donation.setProduct(Product.valueOf(donationDto.getProduct().toUpperCase()));
        donation.setDetails(donationDto.getDetails());
        return donation;
    }

    public DonationDto map(Donation donation){
        DonationDto donationDto = new DonationDto();
        donationDto.setProduct(String.valueOf(donation.getProduct()).toLowerCase());
        donationDto.setDetails(donation.getDetails());
        return donationDto;
    }

}
