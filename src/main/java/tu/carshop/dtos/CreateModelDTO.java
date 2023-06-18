package tu.carshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tu.carshop.enums.Category;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateModelDTO {

    @NotBlank(message = "Brand name must be provided")
    private String brandName;

    @NotBlank(message = "Model name must be provided")
    private String name;

    @NotNull(message = "Model category must be provided")
    private Category category;

    @Size(min = 8, max = 512, message = "Image URL must be between 8 and 512 characters length!")
    private String imageURL;

    private LocalDate startYear;

    private LocalDate endYear;
}
