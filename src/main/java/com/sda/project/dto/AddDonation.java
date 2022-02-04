package com.sda.project.dto;

import com.sda.project.model.Pet;
import com.sda.project.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// the donation info required to save a new donation
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddDonation {

    // logged user id
    private Long userId;
    private Product product;
    private String details;
    private String email;

}
