package com.sda.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pet_appointment",
            joinColumns = @JoinColumn(
                    name = "appointment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "pet_id", referencedColumnName = "id"))
    private Set<Pet> pets = new HashSet<>();

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.getAppointments().add(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
