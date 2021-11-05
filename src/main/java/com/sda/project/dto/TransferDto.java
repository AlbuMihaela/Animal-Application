package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransferDto {

    private String cardholderName;
    private String cardNumber;
    private String cardExpirationDate;
    private String cvc;
    private String amount;
    private LocalDate transferDate;
}
