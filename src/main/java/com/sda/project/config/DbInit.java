package com.sda.project.config;

import com.sda.project.controller.exception.ResourceAlreadyExistsException;
import com.sda.project.dto.PetDto;
import com.sda.project.mapper.AdoptionMapper;
import com.sda.project.mapper.PetMapper;
import com.sda.project.model.*;
import com.sda.project.repository.*;
import com.sda.project.service.AdoptionService;
import com.sda.project.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Configuration
public class DbInit {

    private static final Logger log = LoggerFactory.getLogger(DbInit.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private AdoptionMapper adoptionMapper;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetService petService;



    @Bean
    public CommandLineRunner initialData() {
        return args -> {
            log.info("setup initial data");

            // create privileges
            Privilege readPrivilege = createPrivilegeIfNotFound(PrivilegeType.READ_PRIVILEGE);
            Privilege writePrivilege = createPrivilegeIfNotFound(PrivilegeType.WRITE_PRIVILEGE);

            // create roles
            createRoleIfNotFound(RoleType.ADMIN, Set.of(readPrivilege, writePrivilege));
            createRoleIfNotFound(RoleType.USER, Set.of(readPrivilege, writePrivilege));

            // create main admin, admin, user
            User mainAdmin = createMainAdmin();
            userRepository.save(mainAdmin);

            User admin = createAdmin();
            userRepository.save(admin);

            User user = createUser();
            userRepository.save(user);

            Pet mikeTheDog = createDog();
            petRepository.save(mikeTheDog);

            Pet rexTheDog = createDog2();
            petRepository.save(rexTheDog);

            Pet cat = createCat();
            petRepository.save(cat);

            PetDto catDto = createPetDto();
            petService.update2(catDto);

            Appointment appointment = createAppointment(cat, mikeTheDog);
            user.addAppointment(appointment);
            appointmentRepository.save(appointment);

            // after creating an entity relationship
            // create paren
            // create child
            // set child on parent or add parent to child

            Adoption adoption = createAdoption(mikeTheDog, user);
                   // save child or parent

            adoptionRepository.save(adoption);

            Donation donation = createDonation();
            user.addDonation(donation);
            donationRepository.save(donation);

            Transfer transfer = createTransfer(user);
            user.addTransfer(transfer);
            donation.addTransfer(transfer);
            transferRepository.save(transfer);
        };
    }


    private User createMainAdmin() {
        User admin = new User(
                "main@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "jon",
                "snow");
        Role adminRole = roleRepository.findByType(RoleType.ADMIN).orElseThrow();
        admin.addRole(adminRole);
        return admin;
    }

    private User createAdmin() {
        User admin = new User(
                "admin@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "bill",
                "clinton");
        Role adminRole = roleRepository.findByType(RoleType.ADMIN).orElseThrow();
        admin.addRole(adminRole);
        return admin;
    }

    private User createUser() {
        User user = new User(
                "user@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "alex",
                "vasile");
        user.setPhoneNumber("0721459099");
        Role userRole = roleRepository.findByType(RoleType.USER).orElseThrow();
        user.addRole(userRole);
        return user;
    }

    private Pet createDog() {
        Pet pet = new Pet();
        pet.setName("Mike");
        pet.setCategory(Category.DOG);
        pet.setDescription("small dog, brown with white spots");
        pet.setAvailable(true);
        return pet;
    }

    private Pet createDog2() {
        Pet pet = new Pet();
        pet.setName("Rex");
        pet.setCategory(Category.DOG);
        pet.setDescription("cute dog");
        pet.setAvailable(true);
        return pet;
    }

    private Pet createCat() {
        Pet pet = new Pet();
        pet.setName("Mussy");
        pet.setCategory(Category.CAT);
        pet.setDescription("small cat, blue eyes");
        pet.setAvailable(true);
        return pet;
    }



    private Adoption createAdoption(Pet pet, User user) {
        Adoption adoption = new Adoption();
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setSocialSecurityNumber("1871230556677");
        adoption.setAddress("str. Calea Victoriei nr.1, bl. 1, sc. 1, et. 1, ap. 1, sector 1, Bucuresti");
        adoption.setPet(pet);
        pet.setAvailable(false);

        adoption.setUser(user);

        return adoption;
    }

    private Appointment createAppointment(Pet pet1, Pet pet2) {
        Appointment appointment = new Appointment();
        appointment.setDate(LocalDateTime.now());
        appointment.addPet(pet1);
        appointment.addPet(pet2);
        return appointment;
    }

    private Donation createDonation() {
        Donation donation = new Donation();
        donation.setProduct(Product.FOOD);
        donation.setDetails("Purina One");
        return donation;
    }

    private PetDto createPetDto(){
        PetDto pet = new PetDto();
        pet.setId(3L);
        pet.setName("Mussy");
        pet.setCategory(String.valueOf(Category.CAT));
        pet.setDescription("big cat, green eyes");
        pet.setAvailable(true);
        return pet;
    }

    private Transfer createTransfer(User user) {
        Transfer transfer = new Transfer();
        transfer.setCardholderName("Mihaela Albu");
        transfer.setCardNumber("ROINGB000099997321");
        transfer.setCardExpirationDate(LocalDate.now());
        transfer.setCvc("042");
        transfer.setAmount(300.00);
        transfer.setTransferDate(LocalDate.now());
        return transfer;
    }


    @Transactional
    Role createRoleIfNotFound(RoleType type, Set<Privilege> privileges) {
        return (Role) roleRepository.findByType(type)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("role already exists");
                })
                .orElseGet(() -> {
                    Role role = new Role(type);
                    role.setPrivileges(privileges);
                    return roleRepository.save(role);
                });
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(PrivilegeType name) {
        return (Privilege) privilegeRepository.findByType(name)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("privilege already exists");
                })
                .orElseGet(() -> privilegeRepository.save(new Privilege(name)));
    }


}
