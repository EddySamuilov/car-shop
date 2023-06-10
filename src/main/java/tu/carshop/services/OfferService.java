package tu.carshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.mapper.OfferMapper;
import tu.carshop.repositories.OfferRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferService extends BaseService<OfferDTO> {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    @Override
    @Transactional(readOnly = true)
    protected List<OfferDTO> getAll() {
        return offerRepository.findAll()
                .stream()
                .map(offerMapper::toDTO)
                .toList();
    }

    @Override
    protected OfferDTO findById(Long id) {
        return offerRepository.findById(id)
                .map(offerMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    @Override
    protected boolean deleteById(Long id) {
        return false;
    }

    @Override
    protected OfferDTO update() {
        return null;
    }

}
