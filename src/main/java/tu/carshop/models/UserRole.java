package tu.carshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import tu.carshop.enums.Role;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class UserRole extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Role role;

}