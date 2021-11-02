package com.sda.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

// lombok annotations
@Getter
@Setter
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    private String description;

    @OneToOne
    @JoinColumn(name = "adoption_id")
    private Adoption adoption;

    @ManyToMany(mappedBy = "pets")
    private List<Appointment> appointments;
}
