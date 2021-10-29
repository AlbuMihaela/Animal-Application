package com.sda.project.controller;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class TransferController {


    // FIXME: implement later
    @GetMapping("/transfers")
    public String getTransferPage() {
        return "user/transfers";
    }
}

