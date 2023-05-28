package tu.carshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import tu.carshop.enums.Category;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ModelDTO {

    @NotBlank
    private String name;

    private Category category;

    @Size(min = 8, max = 512, message = "Image URL must be between 8 and 512 characters length!")
    private String imageURL;

    private LocalDate startYear;

    private LocalDate endYear;
}
