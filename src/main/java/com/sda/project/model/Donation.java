package com.sda.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Product product;

    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "donation",
            fetch = FetchType.LAZY)
    private Transfer transfer;

    public void addTransfer(Transfer transfer) {
        this.transfer = transfer;
        transfer.setDonation(this);
    }
}
