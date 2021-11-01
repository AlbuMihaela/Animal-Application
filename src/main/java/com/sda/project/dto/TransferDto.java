package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDto {

    private String cardholderName;
    private String cardNumber;
    private String cardExpirationDate;
    private String cvc;
    private String amount;
}
