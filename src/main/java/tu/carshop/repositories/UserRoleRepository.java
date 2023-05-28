package tu.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tu.carshop.models.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
