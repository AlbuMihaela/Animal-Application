package com.sda.project.service;

import com.sda.project.dto.TransferDto;
import com.sda.project.dto.TransferInfo;
import com.sda.project.mapper.TransferMapper;
import com.sda.project.model.Transfer;
import com.sda.project.model.User;
import com.sda.project.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;

    @Autowired
    public TransferService(TransferRepository transferRepository,
                           TransferMapper transferMapper) {
        this.transferRepository = transferRepository;
        this.transferMapper = transferMapper;
    }


    public Transfer save(TransferDto transferDto, User user) {
        Transfer transfer = transferMapper.map(transferDto);
        user.addTransfer(transfer);
        return transferRepository.save(transfer);
    }

    public List<TransferDto> findAll() {
        return transferRepository.findAll().stream()
                .map(transfer -> transferMapper.map(transfer))
                .collect(Collectors.toList());
    }

    public Set<TransferInfo> findTransfersByUser(User user) {
        return transferRepository.findAll().stream()
                .filter(transfer -> transfer.getUser().equals(user))
                .map(transfer -> transferMapper.mapFromTransferToTransferInfo(transfer))
                .collect(Collectors.toSet());
    }
}
