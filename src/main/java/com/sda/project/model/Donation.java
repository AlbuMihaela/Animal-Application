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
    private Product products;//product// //TODO check with Mada
    private String details;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToOne(mappedBy = "donation")
    private Transfer transfer;
}
