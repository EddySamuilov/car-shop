package tu.carshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tu.carshop.enums.Engine;
import tu.carshop.enums.Transmission;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "offers")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Offer extends BaseEntity {

    private String description;

    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Size(min = 8, max = 512, message = "Image URL must be between 8 and 512 characters length!")
    @Column(name = "image_url")
    private String imageURL;

    private int mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private LocalDate year;

    @ManyToOne
    private Model model;

//    @ManyToOne
//    private User seller;
}