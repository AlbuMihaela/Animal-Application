package com.sda.project.dto;

import com.sda.project.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonationInfo {

    // logged user id
    private UserInfoDto user;
    private Product product;
    private String details;
}
