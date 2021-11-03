package com.sda.project.mapper;

import com.sda.project.dto.DonationAdd;
import com.sda.project.model.Donation;
import com.sda.project.model.Product;
import org.springframework.stereotype.Service;

@Service
public class DonationMapper {

    public Donation map(DonationAdd dto){
        Donation entity = new Donation();
        entity.setProduct(dto.getProduct());
        entity.setDetails(dto.getDetails());
        return entity;
    }

    public DonationAdd map(Donation entity){
        DonationAdd dto = new DonationAdd();
        dto.setProduct(entity.getProduct());
        dto.setDetails(entity.getDetails());
        return dto;
    }
}
