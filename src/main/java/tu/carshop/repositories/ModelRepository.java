package tu.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tu.carshop.models.Model;

import java.util.Set;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Model findByName(String name);
    Set<Model> findAllByBrandName(String brandName);
}
