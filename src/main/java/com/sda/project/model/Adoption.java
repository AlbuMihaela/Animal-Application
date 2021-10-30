package com.sda.project.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    private String proofOfAddress;
    private String proofOfFinancialSituation;
    private String identityCard;


    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "adoption")
    private Pet pet;
}
