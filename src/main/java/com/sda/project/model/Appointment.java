package com.sda.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime date;
    private AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(name = "pet_appointment",
            joinColumns = @JoinColumn(
                    name = "appointment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "pet_id", referencedColumnName = "id"))
    private List<Pet> pets;


}
