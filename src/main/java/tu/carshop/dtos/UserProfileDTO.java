package tu.carshop.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    @NotBlank
    @Size(min = 3)
    private String username;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String imageURL;
}
