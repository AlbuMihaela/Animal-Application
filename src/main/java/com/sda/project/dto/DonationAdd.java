package com.sda.project.dto;

import com.sda.project.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DonationAdd {

    // logged user id
    private Long userId;
    private Product product;
    private String details;
}
