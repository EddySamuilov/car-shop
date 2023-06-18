package tu.carshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tu.carshop.dtos.CreateOfferDTO;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.exceptions.ObjectNotFoundException;
import tu.carshop.mapper.OfferMapper;
import tu.carshop.models.Model;
import tu.carshop.models.Offer;
import tu.carshop.models.User;
import tu.carshop.repositories.ModelRepository;
import tu.carshop.repositories.OfferRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferService extends BaseService<OfferDTO> {
    private static final String OFFER_NOT_FOUND_ERROR_MESSAGE = "Offer with id %s not found!";

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final UserService userService;
    private final ModelRepository modelRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OfferDTO> getAll() {
        return offerRepository.findAll()
                .stream()
                .map(offerMapper::toDTO)
                .toList();
    }

    @Override
    public OfferDTO findById(Long id) {
        return offerRepository.findById(id)
                .map(offerMapper::toDTO)
                .orElseThrow(() -> new ObjectNotFoundException(String.format(OFFER_NOT_FOUND_ERROR_MESSAGE, id)));
    }

    @Override
    public boolean deleteById(Long id) {
        offerRepository.deleteById(id);
        return true;
    }

    @Override
    public OfferDTO update(Long id, OfferDTO updateDto) {
        Offer offer = offerRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException(String.format(OFFER_NOT_FOUND_ERROR_MESSAGE, id)));

        offer.setDescription(updateDto.getDescription());
        offer.setEngine(updateDto.getEngine());
        offer.setMileage(updateDto.getMileage());
        offer.setImageURL(updateDto.getImageURL());
        offer.setPrice(updateDto.getPrice());
        offer.setTransmission(updateDto.getTransmission());
        offer.setYear(updateDto.getYear());
        offer.setModified(LocalDateTime.now());

        return offerMapper.toDTO(offerRepository.save(offer));
    }

    public OfferDTO create(CreateOfferDTO createOfferDTO, String username) {
        User user = userService.findByUsername(username);
        Model model = modelRepository.findById(createOfferDTO.getModelId()).orElseThrow();

        Offer offer = offerMapper.toEntity(createOfferDTO);
        offer.setSeller(user);
        offer.setModel(model);
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());

        return offerMapper.toDTO(offerRepository.save(offer));
    }
}
