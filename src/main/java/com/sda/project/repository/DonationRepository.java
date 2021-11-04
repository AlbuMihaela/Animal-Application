package com.sda.project.repository;

import com.sda.project.model.Donation;
import com.sda.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation,Long> {

}
