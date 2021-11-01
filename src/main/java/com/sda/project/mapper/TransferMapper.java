package com.sda.project.mapper;

import com.sda.project.dto.TransferDto;
import com.sda.project.model.Transfer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransferMapper {

    public Transfer map(TransferDto transferDto) {
        Transfer transfer = new Transfer();
        transfer.setCardholderName(transferDto.getCardholderName());
        transfer.setCardNumber(transferDto.getCardNumber());
        transfer.setCardExpirationDate(LocalDate.parse(transferDto.getCardExpirationDate()));
        transfer.setCvc(transferDto.getCvc());
        transfer.setAmount(Double.valueOf(transferDto.getAmount()));
        return transfer;
    }

    public TransferDto map(Transfer transfer) {
        TransferDto transferDto = new TransferDto();
        transferDto.setCardholderName(transfer.getCardholderName());
        transferDto.setCardNumber(transfer.getCardNumber());
        transferDto.setCardExpirationDate(String.valueOf(transfer.getCardExpirationDate()));
        transferDto.setCvc(transfer.getCvc());
        transferDto.setAmount(String.valueOf(transfer.getAmount()));
        return transferDto;
    }
}
