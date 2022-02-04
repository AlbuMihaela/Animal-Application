package com.sda.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean enabled;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Adoption> adoptions = new HashSet<>();

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Donation> donations = new HashSet<>();

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    private Set<Transfer> transfers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
        this.enabled = true;
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = true;
    }

    // helper method for bidirectional synchronization
    public void addAdoption(Adoption adoption) {
        // add child to parent
        this.adoptions.add(adoption);

        // add parent to child
        adoption.setUser(this);
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setUser(this);
    }

    public void addDonation(Donation donation) {
        this.donations.add(donation);
        donation.setUser(this);
    }

    public void addTransfer(Transfer transfer) {
        this.transfers.add(transfer);
        transfer.setUser(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public Set<Adoption> getAdoptions() {
        return adoptions;
    }

    public Set<Donation> getDonations() {
        return donations;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

