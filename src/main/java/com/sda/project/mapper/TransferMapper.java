package com.sda.project.mapper;

import com.sda.project.dto.TransferDto;
import com.sda.project.dto.TransferInfo;
import com.sda.project.model.Transfer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Component
public class TransferMapper {

    public Transfer map(TransferDto transferDto) {
        Transfer transfer = new Transfer();
        transfer.setCardholderName(transferDto.getCardholderName());
        transfer.setCardNumber(transferDto.getCardNumber());
        transfer.setCardExpirationDate(LocalDate.parse(transferDto.getCardExpirationDate()));
        transfer.setCvc(transferDto.getCvc());
        transfer.setAmount(Double.valueOf(transferDto.getAmount()));
        transfer.setTransferDate(LocalDate.now());
        return transfer;
    }

    public TransferDto map(Transfer transfer) {
        TransferDto transferDto = new TransferDto();
        transferDto.setCardholderName(transfer.getCardholderName());
        transferDto.setCardNumber(transfer.getCardNumber());
        transferDto.setCardExpirationDate(String.valueOf(transfer.getCardExpirationDate()));
        transferDto.setCvc(transfer.getCvc());
        transferDto.setAmount(String.valueOf(transfer.getAmount()));
        transferDto.setTransferDate(transfer.getTransferDate());
        return transferDto;
    }

    public TransferInfo mapFromTransferToTransferInfo(Transfer transfer) {
        TransferInfo dto = new TransferInfo();
        dto.setTransferDate(transfer.getTransferDate());
        dto.setAmount(transfer.getAmount());
        return dto;
    }

}
