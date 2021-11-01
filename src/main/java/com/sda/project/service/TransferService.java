package com.sda.project.service;

import com.sda.project.dto.TransferDto;
import com.sda.project.mapper.TransferMapper;
import com.sda.project.model.Transfer;
import com.sda.project.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public Transfer save(TransferDto transferDto) {
        return transferRepository.save(transferMapper.map(transferDto));
    }
    //TODO ?? Este mai bine ca o functie de save sa returneze un obiect sau void???

    public List<TransferDto> findAll() {
        return transferRepository.findAll().stream()
                .map(transfer -> transferMapper.map(transfer))
                .collect(Collectors.toList());
    }
}
