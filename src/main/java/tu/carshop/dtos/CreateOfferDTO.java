package tu.carshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
public class CreateOfferDTO {

    private Long id;
    @NotNull
    private Long modelId;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Engine engine;
    @NotNull
    private Transmission transmission;
    @NotNull
    private LocalDate year;
    @NotNull
    @PositiveOrZero
    private Integer mileage;
    @NotBlank
    private String description;
    private String imageURL;
}
