package tu.carshop.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.enums.Engine;
import tu.carshop.enums.Transmission;
import tu.carshop.mapper.OfferMapper;
import tu.carshop.mapper.OfferMapperImpl;
import tu.carshop.models.Model;
import tu.carshop.models.Offer;
import tu.carshop.models.User;
import tu.carshop.repositories.ModelRepository;
import tu.carshop.repositories.OfferRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class OfferServiceTest {

  private static final String DESCRIPTION = "description";
  private static final int MILEAGE = 123;
  private static final BigDecimal PRICE = BigDecimal.valueOf(15000);

  @Mock
  private OfferRepository offerRepository;

  @Mock
  private UserService userService;

  @Mock
  private ModelRepository modelRepository;

  private OfferMapper offerMapper;

  private OfferService offerService;

  @BeforeEach
  public void setup() {
    offerMapper = new OfferMapperImpl();

    offerService = new OfferService(
        offerRepository,
        offerMapper,
        userService,
        modelRepository
    );
  }

  @Test
  public void getAllReturnsCorrectResult() {
    // Arrange
    List<Offer> offers = createOffers();

    when(offerRepository.findAll()).thenReturn(offers);

    // Act
    List<OfferDTO> result = offerService.getAll();

    // Assert
    OfferDTO expectedOffer = result.stream()
        .filter(offer -> offer.getDescription().equals(DESCRIPTION))
        .findAny()
        .orElseThrow();

    assertAll(
        () -> verify(offerRepository, times(1)).findAll(),

        () -> assertEquals(1, result.size()),
        () -> assertEquals(DESCRIPTION, expectedOffer.getDescription()),
        () -> assertEquals(Engine.DIESEL, expectedOffer.getEngine()),
        () -> assertEquals(MILEAGE, expectedOffer.getMileage()),
        () -> assertEquals(PRICE, expectedOffer.getPrice()),
        () -> assertEquals(Transmission.AUTOMATIC, expectedOffer.getTransmission())
    );
  }

  private List<Offer> createOffers() {
    Offer offer = new Offer(
        DESCRIPTION,
        Engine.DIESEL,
        "no image",
        MILEAGE,
        PRICE,
        Transmission.AUTOMATIC,
        LocalDate.of(2021, 1, 1),
        new Model(),
        new User(),
        new ArrayList<>()
    );

    offer.setCreated(LocalDateTime.now());
    offer.setModified(LocalDateTime.now());

    return Collections.singletonList(offer);
  }
}
