package tu.carshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tu.carshop.models.Brand;
import tu.carshop.models.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
