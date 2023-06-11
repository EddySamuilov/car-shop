package tu.carshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tu.carshop.enums.Engine;
import tu.carshop.enums.Transmission;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private Long id;
    private String description;
    private Engine engine;
    private String imageURL;
    private int mileage;
    private BigDecimal price;
    private Transmission transmission;
    private LocalDate year;
    private String model;
    private String brand;
    private LocalDate created;
    private LocalDate modified;
}
