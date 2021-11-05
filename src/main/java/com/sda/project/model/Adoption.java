package com.sda.project.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "adoption")
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate adoptionDate;
    private String proofOfAddress;
    private String proofOfFinancialSituation;
    private String socialSecurityNumber;

    // adoption edit form, change user
    // adoption knows his user
    // bidirectional many to one
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
            (mappedBy = "adoption",
                    fetch = FetchType.LAZY)
    private Pet pet;

    public Pet getPet() {
        return pet;
    }
}
