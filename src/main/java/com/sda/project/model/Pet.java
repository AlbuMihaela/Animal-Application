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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adoption_id")
    private Adoption adoption;

    @ManyToMany(
            mappedBy = "pets",
            fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    //TODO check this method
    public void setAdoption(Adoption adoption) {
        adoption.setPet(this);
    }

    public void addAppointments(Set<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            this.appointments.add(appointment);
//            set pet list on each appointment
            appointment.setPets(appointment.getPets());
        }
        //TODO please check this method


    }

}
