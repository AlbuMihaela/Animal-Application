package com.sda.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransferController {


    // FIXME: implement later
    @GetMapping("/transfer-add/")
    public String getTransferForm() {
        return "transfer/transfer-add";
    }
}

