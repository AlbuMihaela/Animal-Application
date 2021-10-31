package com.sda.project.model;

import com.sda.project.model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Product products;
    private String details;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToOne(mappedBy = "donation")
    private Transfer transfer;
}
