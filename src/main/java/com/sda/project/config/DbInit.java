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
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private TransferRepository transferRepository;

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

            Pet zynTheDog = createDog3();
            petRepository.save(zynTheDog);

            Pet sparkTheDog = createDog4();
            petRepository.save(sparkTheDog);

            Pet jackTheDog = createDog5();
            petRepository.save(jackTheDog);

            Pet cat = createCat();
            petRepository.save(cat);

            Pet siriTheCat = createCat1();
            petRepository.save(siriTheCat);

            Pet lunaTheCat = createCat2();
            petRepository.save(lunaTheCat);

            Pet gremTheCat = createCat3();
            petRepository.save(gremTheCat);

            Pet lizzTheCat = createCat4();
            petRepository.save(lizzTheCat);

            Pet bird = createBird();
            petRepository.save(bird);

            Pet rocTheBird = createBird2();
            petRepository.save(rocTheBird);

            Pet pepperTheRabbit = createRabbit1();
            petRepository.save(pepperTheRabbit);

            Pet peterTheRabbit = createRabbit2();
            petRepository.save(peterTheRabbit);

            Pet harryTheGuinea = createGuinea1();
            petRepository.save(harryTheGuinea);

            Pet raffTheGuinea = createGuinea2();
            petRepository.save(raffTheGuinea);

            Pet ambraTheDog = createDog6();
            petRepository.save(ambraTheDog);

            Appointment appointment = createAppointment(cat, mikeTheDog);
            user.addAppointment(appointment);
            appointmentRepository.save(appointment);

            Adoption adoption = createAdoption(mikeTheDog, user);
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
        pet.setDescription("Mike is an active boy looking for an active home. He is in need of some training and socialization with other dogs. Is friendly, playful, athletic with a white colour as snow");
        pet.setAvailable(true);
        pet.setPhoto("Mike.jpg");
        return pet;
    }

    private Pet createDog2() {
        Pet pet = new Pet();
        pet.setName("Rex");
        pet.setCategory(Category.DOG);
        pet.setDescription("White dog with brown ears");
        pet.setAvailable(true);
        pet.setPhoto("rex.jpg");
        return pet;
    }

    private Pet createDog3() {
        Pet pet = new Pet();
        pet.setName("Zyn");
        pet.setCategory(Category.DOG);
        pet.setDescription("Zyn is a 5–6-month-old neutered male happy, friendly and goofy pup. He would love a home and humans committed to teaching him canine good citizenship and providing opportunities for exercise and fun!");
        pet.setAvailable(true);
        pet.setPhoto("Zyn.jpg");
        return pet;
    }

    private Pet createDog4() {
        Pet pet = new Pet();
        pet.setName("Spark");
        pet.setCategory(Category.DOG);
        pet.setDescription("Spark is an 8 week old smart and playful dog.He is absolutely the sweetest boy and has just as much energy as his sweetness. He loves to play until he just drops");
        pet.setAvailable(true);
        pet.setPhoto("Spark.jpg");
        return pet;
    }

    private Pet createDog5() {
        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setCategory(Category.DOG);
        pet.setDescription("Jack is a big beautiful boy looking for a new home. He needs training to be better socialized as he can be selective with other dogs. He loves people and food!");
        pet.setAvailable(true);
        pet.setPhoto("Jack.jpg");
        return pet;
    }

    private Pet createCat() {
        Pet pet = new Pet();
        pet.setName("Mussy");
        pet.setCategory(Category.CAT);
        pet.setDescription("small cat, blue eyes");
        pet.setAvailable(true);
        pet.setPhoto("Mussy.jpg");
        return pet;
    }

    private Pet createCat1() {
        Pet pet = new Pet();
        pet.setName("Siri");
        pet.setCategory(Category.CAT);
        pet.setDescription("Siri is a quiet and gently little girl. She is just over 1 year old. Is friendly, affectionate, gentle, playful, smart, curious, funny, loves kisses");
        pet.setAvailable(true);
        pet.setPhoto("Siri.jpg");
        return pet;
    }

    private Pet createCat2() {
        Pet pet = new Pet();
        pet.setName("Luna");
        pet.setCategory(Category.CAT);
        pet.setDescription("Luna is a 10 year old female who is quiet and keeps to herself. She loves to snuggle and will make sure your bed is kept warm while you are away.She is good with cats, dogs and kids.");
        pet.setAvailable(true);
        pet.setPhoto("Luna.jpg");
        return pet;
    }

    private Pet createCat3() {
        Pet pet = new Pet();
        pet.setName("Grem");
        pet.setCategory(Category.CAT);
        pet.setDescription("Grem is around 9 or 10 years old and is such a lover. He loves to cuddle with his people and will gladly sit on your lap and purr to his heart's content");
        pet.setAvailable(true);
        pet.setPhoto("Grem.jpg");
        return pet;
    }

    private Pet createCat4() {
        Pet pet = new Pet();
        pet.setName("Lizz");
        pet.setCategory(Category.CAT);
        pet.setDescription("Is a very special girl who needs a patient human that wants to learn her behaviors and her quirky personality.");
        pet.setAvailable(true);
        pet.setPhoto("Lizz.jpg");
        return pet;
    }


    private Pet createBird() {
        Pet pet = new Pet();
        pet.setName("Coco");
        pet.setCategory(Category.BIRD);
        pet.setDescription("Red bird with blue wings. Shy bird by nature. Is a great choice for beginning pet parents.");
        pet.setAvailable(true);
        pet.setPhoto("Coco.jpg");
        return pet;
    }

    private Pet createBird2() {
        Pet pet = new Pet();
        pet.setName("Roc");
        pet.setCategory(Category.BIRD);
        pet.setDescription("Roc is a small and colorful bird. Is the best singer");
        pet.setAvailable(true);
        pet.setPhoto("Roc.jpg");
        return pet;
    }

    private Pet createRabbit1() {
        Pet pet = new Pet();
        pet.setName("Pepper");
        pet.setCategory(Category.RABBIT);
        pet.setDescription("Pepper is a foodie with a passion for coriander. He will climb on our laps to get closer to the salad bowl and is happy to be stroked and petted. Pepper will stretch up to greet you and is calm sweet boy.");
        pet.setAvailable(true);
        pet.setPhoto("Pepper.jpg");
        return pet;
    }

    private Pet createRabbit2() {
        Pet pet = new Pet();
        pet.setName("Peter");
        pet.setCategory(Category.RABBIT);
        pet.setDescription("Peter is a 6 year old bunny; He likes his treats and fresh daily greens. Peter would be best in a quiet home with someone willing to earn his trust.");
        pet.setAvailable(true);
        pet.setPhoto("Peter.jpg");
        return pet;
    }

    private Pet createGuinea1() {
        Pet pet = new Pet();
        pet.setName("Harry");
        pet.setCategory(Category.GUINEA_PIG);
        pet.setDescription("Harry was born on 19th June 2020.He is looking for a loving forever home. Harry has a long coat and will need to be regularly groomed.");
        pet.setAvailable(true);
        pet.setPhoto("Harry.jpg");
        return pet;
    }

    private Pet createGuinea2() {
        Pet pet = new Pet();
        pet.setName("Raff");
        pet.setCategory(Category.GUINEA_PIG);
        pet.setDescription("Raff was born on 20th January 2020. He is cute and very naughty so will need an experienced guinea pig home who will appreciate his great sense of humour.");
        pet.setAvailable(true);
        pet.setPhoto("Raff.jpg");
        return pet;
    }

    private Pet createDog6() {
        Pet pet = new Pet();
        pet.setName("Ambra");
        pet.setCategory(Category.DOG);
        pet.setDescription("Ambra is a big beautiful black girl looking for a new home. She is playful and love the kids.");
        pet.setAvailable(true);
        pet.setPhoto("Ambra.png");
        return pet;
    }


    private Adoption createAdoption(Pet pet, User user) {
        Adoption adoption = new Adoption();
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setSocialSecurityNumber("1871230556677");
        adoption.setAddress("str. Calea Victoriei nr.1, bl. 1, sc. 1, et. 1, ap. 1, sector 1, Bucuresti");
        adoption.setPet(pet);
        pet.setAvailable(false);
        petRepository.save(pet);
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
