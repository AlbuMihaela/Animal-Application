package com.sda.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransferInfo {

    private Double amount;
    private LocalDate transferDate;
}
