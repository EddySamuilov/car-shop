package tu.carshop.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDTO {

    @NotBlank
    @Size(min = 3)
    private String username;

    private String firstName;

    private String lastName;

    @NotBlank
    @Size(min = 3)
    private String password;

    private String confirmPassword;

    @Email
    private String email;

}
