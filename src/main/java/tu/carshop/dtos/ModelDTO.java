package tu.carshop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import tu.carshop.enums.Category;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ModelDTO {

    private String name;
    private Category category;
    private String imageURL;
    private LocalDate startYear;
    private LocalDate endYear;
}
