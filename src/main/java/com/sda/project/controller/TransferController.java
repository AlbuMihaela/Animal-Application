package com.sda.project.controller;

import com.sda.project.dto.TransferDto;
import com.sda.project.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/transfers")
    public String getTransfersPage(Model model) {
        model.addAttribute("transfersDto", transferService.findAll());
        return "transfer/transfers";
    }

    // FIXME: implement later
    @GetMapping("/transfers/add")
    public String getTransferForm(Model model) {
        model.addAttribute("transferDto", new TransferDto());
        return "transfer/transfer-add";
    }

    @PostMapping("transfers/add")
    public String addDonationForm(@ModelAttribute("transferDto") TransferDto transferDto) {
        transferService.save(transferDto);
        return "redirect:/home";
    }

}

