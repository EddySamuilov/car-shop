package tu.carshop.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tu.carshop.enums.Category;
import tu.carshop.models.BaseEntity;
import tu.carshop.models.Brand;
import tu.carshop.models.Model;
import tu.carshop.repositories.BrandRepository;
import tu.carshop.repositories.ModelRepository;
import tu.carshop.repositories.OfferRepository;
import tu.carshop.repositories.UserRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count() != 0) {
            return;
        }

        Brand audi = new Brand("Audi");
        Brand bmw = new Brand("BMW");
        Brand mercedes = new Brand("Mercedes");
        Brand lada = new Brand("Lada");

        Model a4 = new Model(
                "A4",
                Category.CAR,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdL2HnU-lDeFj9FNl45OScEyadRcPz6WNZvqj0AyXP&s",
                LocalDate.of(2004, 1, 1),
                LocalDate.of(2007, 1, 1),
                audi
        );

        Model s5 = new Model(
                "S5",
                Category.CAR,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCsfsqehlQvVc5uCdF1O4FrMIk1Qh25GkinWIUTcYzQQ&s",
                LocalDate.of(2008, 1, 1),
                LocalDate.of(2014, 1, 1),
                audi
        );

        Model eqPower = new Model(
                "EQ Power",
                Category.TRUCK,
                "https://mir-s3-cdn-cf.behance.net/project_modules/1400/8f81ad59632289.5b0c55f2a684d.jpg",
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2022, 1, 1),
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


        setTimestamps(List.of(audi, bmw, mercedes, lada, a4, s5, eqPower, amg, i8));
        brandRepository.saveAll(List.of(audi, bmw, mercedes, lada));
        modelRepository.saveAll(List.of(a4, s5, eqPower, amg, i8));
    }

    private void setTimestamps(List<? extends BaseEntity> collection) {
        collection.forEach(element -> {
            element.setCreated(Instant.now());
            element.setModified(Instant.now());
        });
    }
}
