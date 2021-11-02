package com.sda.project.config;

import com.sda.project.controller.exception.ResourceAlreadyExistsException;
import com.sda.project.dto.PetDto;
import com.sda.project.model.*;
import com.sda.project.repository.AdoptionRepository;
import com.sda.project.repository.PetRepository;
import com.sda.project.repository.PrivilegeRepository;
import com.sda.project.repository.RoleRepository;
import com.sda.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

            Pet dog = createDog();
            petRepository.save(dog);

            Pet cat = createCat();
            petRepository.save(cat);

            // after creating an entity relationship
            // create parent
            // create child
            Adoption adoption = createAdoption();
            // set child on parent or add parent to child
            admin.addAdoption(adoption);
            // save child or parent
            adoptionRepository.save(adoption);
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
        Role userRole = roleRepository.findByType(RoleType.USER).orElseThrow();
        user.addRole(userRole);
        return user;
    }

    private Pet createDog() {
        Pet pet = new Pet();
        pet.setName("Mike");
        pet.setCategory(Category.DOG);
        pet.setDescription("small dog, brown with white spots");
        return pet;
    }

    private Pet createCat() {
        Pet pet = new Pet();
        pet.setName("Mussy");
        pet.setCategory(Category.CAT);
        pet.setDescription("small cat, blue eyes");
        return pet;
    }

    private Adoption createAdoption() {
        Adoption adoption = new Adoption();
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setSocialSecurityNumber("1871230556677");
        adoption.setProofOfAddress("str. Calea Victoriei nr.1, bl. 1, sc. 1, et. 1, ap. 1, sector 1, Bucuresti");
        adoption.setProofOfFinancialSituation("mare boss");
        return adoption;
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
