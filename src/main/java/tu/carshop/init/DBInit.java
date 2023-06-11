package tu.carshop.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tu.carshop.enums.Category;
import tu.carshop.enums.Engine;
import tu.carshop.enums.Role;
import tu.carshop.enums.Transmission;
import tu.carshop.models.BaseEntity;
import tu.carshop.models.Brand;
import tu.carshop.models.Model;
import tu.carshop.models.Offer;
import tu.carshop.models.User;
import tu.carshop.models.UserRole;
import tu.carshop.repositories.BrandRepository;
import tu.carshop.repositories.ModelRepository;
import tu.carshop.repositories.OfferRepository;
import tu.carshop.repositories.UserRepository;
import tu.carshop.repositories.UserRoleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OfferRepository offerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (brandRepository.count() != 0) {
            return;
        }

        UserRole adminRole = new UserRole();
        adminRole.setRole(Role.ADMIN);
        UserRole userRole = new UserRole();
        adminRole.setRole(Role.USER);
        userRoleRepository.saveAll(List.of(adminRole, userRole));

        User admin = new User();
        admin.setRoles(List.of(adminRole));
        admin.setUsername("admin");
        admin.setEmail("admin@abv.sc");
        admin.setFirstName("admin");
        admin.setLastName("adminov");
        admin.setPassword(passwordEncoder.encode("admin"));
        setTimestamps(List.of(admin));
        userRepository.save(admin);

        Brand audi = new Brand("Audi");
        Brand bmw = new Brand("BMW");
        Brand mercedes = new Brand("Mercedes");
        Brand boing = new Brand("Boing");
        Brand opel = new Brand("Opel");
        Brand volkswagen = new Brand("Volkswagen");

        Model a4 = new Model(
            "A4",
            Category.CAR,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdL2HnU-lDeFj9FNl45OScEyadRcPz6WNZvqj0AyXP&s",
            LocalDate.of(2004, 9, 2),
            LocalDate.of(2007, 2, 21),
            audi
        );

        Model q8 = new Model(
                "Q8",
                Category.SUV,
                "https://www.auto-press.net/gallery/article/images_big/gallery_picture_0573464001667974162.jpg",
                LocalDate.of(2022, 1, 2),
                null,
                audi
        );

        Model s5 = new Model(
            "S5",
            Category.CAR,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCsfsqehlQvVc5uCdF1O4FrMIk1Qh25GkinWIUTcYzQQ&s",
            LocalDate.of(2008, 1, 30),
            LocalDate.of(2014, 5, 5),
            audi
        );

        Model eqPower = new Model(
            "EQ Power",
            Category.TRUCK,
            "https://mir-s3-cdn-cf.behance.net/project_modules/1400/8f81ad59632289.5b0c55f2a684d.jpg",
            LocalDate.of(2017, 3, 17),
            LocalDate.of(2022, 9, 14),
            mercedes
        );

        Model amg = new Model(
            "AMG GT",
            Category.CAR,
            "https://cdn4.focus.bg/fakti/photos/16x9/07c/mercedes-amg-gt-black-series-veche-razpolaga-s-1066-konski-sili-3.jpg",
            LocalDate.of(2015, 11, 1),
            LocalDate.of(2021, 5, 1),
            mercedes
        );

        Model i8 = new Model(
            "I8",
            Category.CAR,
            "https://evmotions.bg/evcarspics/bmw-i8.png",
            LocalDate.of(2020, 11, 1),
            LocalDate.of(2030, 5, 1),
            bmw
        );

        Model x5 = new Model(
            "X5",
            Category.SUV,
            "https://bmw-autobavaria.bg/files/offers_cars/9r76635.png?bf0a78e42d",
            LocalDate.of(2003, 11, 1),
            LocalDate.of(2010, 5, 1),
            bmw
        );

        Model boing747 = new Model(
            "Boing 707",
            Category.PLANE,
            "https://www.moreto.net/imfb/126677.jpg",
            LocalDate.of(1968, 7, 7),
            LocalDate.of(2023, 12, 31),
            boing
        );

        Model vectra = new Model(
            "Vectra",
            Category.CAR,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Opel_Vectra_C_2.2_Direkt_front.JPG/420px-Opel_Vectra_C_2.2_Direkt_front.JPG",
            LocalDate.of(1988, 12, 3),
            LocalDate.of(2010, 11, 21),
            opel
        );

        Model golf2 = new Model(
            "Golf 2",
            Category.CAR,
            "https://www.volkswagenag.com/content/dam/online-kommunikation/brands/corporate/world/presence/stories/2019/10/die-glorreichen-sieben/Golf-2-front.jpg",
            LocalDate.of(1983, 12, 3),
            LocalDate.of(1991, 11, 21),
            volkswagen
        );

        setTimestamps(List.of(audi, bmw, mercedes, boing, opel, volkswagen, a4, s5, eqPower, amg, i8, boing747, vectra, golf2, q8, x5));
        brandRepository.saveAll(List.of(audi, bmw, mercedes, boing, opel, volkswagen));
        modelRepository.saveAll(List.of(a4, s5, eqPower, amg, i8, boing747, vectra, golf2, q8, x5));

        Offer audiA4Offer = Offer.builder()
            .model(modelRepository.findByName("A4"))
            .engine(Engine.DIESEL)
            .transmission(Transmission.MANUAL)
            .mileage(22500)
            .price(BigDecimal.valueOf(10999))
            .year(LocalDate.of(2007, 7, 7))
            .description("Not in a good condition at all, matter of fact I don't wanna sell it anymore")
            .seller(userRepository.findByUsername("admin").orElse(null))
            .imageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdL2HnU-lDeFj9FNl45OScEyadRcPz6WNZvqj0AyXP&s")
            .build();

        Offer amgOffer = Offer.builder()
            .model(modelRepository.findByName("AMG GT"))
            .engine(Engine.JET_FUEL)
            .transmission(Transmission.AUTOMATIC)
            .mileage(10000)
            .price(BigDecimal.valueOf(25678))
            .year(LocalDate.of(2019, 1, 6))
            .description("My grandmothers' car. Used mostly for a street races.")
            .seller(userRepository.findByUsername("admin").orElse(null))
            .imageURL("https://cdn4.focus.bg/fakti/photos/16x9/07c/mercedes-amg-gt-black-series-veche-razpolaga-s-1066-konski-sili-3.jpg")
            .build();

        Offer i8Offer = Offer.builder()
            .model(modelRepository.findByName("I8"))
            .engine(Engine.ELECTRIC)
            .transmission(Transmission.AUTOMATIC)
            .mileage(5321)
            .price(BigDecimal.valueOf(98912))
            .year(LocalDate.of(2021, 4, 21))
            .description("My grand-grandmothers' car. She is not a racer. Rides the car only to the shop for groceries.")
            .seller(userRepository.findByUsername("admin").orElse(null))
            .imageURL("https://evmotions.bg/evcarspics/bmw-i8.png")
            .build();

        Offer vectraOffer = Offer.builder()
            .model(modelRepository.findByName("Vectra"))
            .engine(Engine.GASOLINE)
            .transmission(Transmission.MANUAL)
            .mileage(500000)
            .price(BigDecimal.valueOf(1999))
            .year(LocalDate.of(2005, 4, 21))
            .description("Used for street races. Very comfortable and fast!")
            .seller(userRepository.findByUsername("admin").orElse(null))
            .imageURL("https://www.quality-tuning.eu/images/stories/virtuemart/product/rdx/1914_1.jpg")
            .build();

        Offer golf2Offer = Offer.builder()
            .model(modelRepository.findByName("Golf 2"))
            .engine(Engine.GASOLINE)
            .transmission(Transmission.MANUAL)
            .mileage(555110)
            .price(BigDecimal.valueOf(999))
            .year(LocalDate.of(1999, 1, 1))
            .description("In a very good condition. Real 555110km. Manual transmission. Get in the car and ride!")
            .seller(userRepository.findByUsername("admin").orElse(null))
            .imageURL("https://www.volkswagenag.com/content/dam/online-kommunikation/brands/corporate/world/presence/stories/2019/10/die-glorreichen-sieben/Golf-2-front.jpg")
            .build();

        Offer q8Offer = Offer.builder()
            .model(modelRepository.findByName("Q8"))
            .engine(Engine.HYBRID)
            .transmission(Transmission.AUTOMATIC)
            .mileage(0)
            .price(BigDecimal.valueOf(139000))
            .year(LocalDate.of(2022, 6, 6))
            .description("Brand new car! Everything is new!!!")
            .seller(userRepository.findByUsername("admin").orElse(null))
            .imageURL("https://ev-database.org/img/auto/Audi_Q8-etron-sportback-2023/Audi_Q8-etron-sportback-2023-01.jpg")
            .build();

        setTimestamps(List.of(audiA4Offer, amgOffer, i8Offer, vectraOffer, golf2Offer, q8Offer));
        offerRepository.saveAll(List.of(audiA4Offer, amgOffer, i8Offer, vectraOffer, golf2Offer, q8Offer));

    }

    private void setTimestamps(List<? extends BaseEntity> collection) {
        collection.forEach(element -> {
            element.setCreated(LocalDateTime.now());
            element.setModified(LocalDateTime.now());
        });
    }
}
