package tu.carshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tu.carshop.dtos.StatisticsDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StatistiscService {
  private final UserService userService;
  private final BrandService brandService;
  private final OfferService offerService;

  @Transactional(readOnly = true)
  public StatisticsDTO getStatistics() {
    // Not the best way to do this but experimenting with different approaches in order to retrieve count
    return new StatisticsDTO(
        userService.getUsersCount(),
        offerService.getAll().size(),
        brandService.getAll().size()
    );
  }
}
