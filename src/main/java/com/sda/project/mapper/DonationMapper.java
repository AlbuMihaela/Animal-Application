package com.sda.project.mapper;

import com.sda.project.dto.DonationDto;
import com.sda.project.model.Donation;
import com.sda.project.model.Product;
import org.springframework.stereotype.Service;

@Service
public class DonationMapper {

    public Donation map(DonationDto donationDto){
        Donation donation = new Donation();
        donation.setProducts(Product.valueOf(donationDto.getProducts()));
        donation.setDetails(donationDto.getDetails());
        return donation;
    }

    public DonationDto map(Donation donation){
        DonationDto donationDto = new DonationDto();
        donationDto.setProducts(String.valueOf(donation.getProducts()));
        donationDto.setDetails(donation.getDetails());
        return donationDto;
    }

}
