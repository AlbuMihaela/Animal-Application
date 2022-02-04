package com.sda.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private boolean isAvailable;
    private String photo;

    @OneToOne(mappedBy = "pet",
            fetch = FetchType.LAZY)
    private Adoption adoption;

    @ManyToMany(
            mappedBy = "pets",
            fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    public Adoption getAdoption() {
        return adoption;
    }

    public void setAdoption(Adoption adoption) {
        this.adoption = adoption;
    }

}


