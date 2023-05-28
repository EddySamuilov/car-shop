package tu.carshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tu.carshop.dtos.ModelDTO;
import tu.carshop.mapper.ModelMapper;
import tu.carshop.repositories.ModelRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ModelService extends BaseService<ModelDTO> {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ModelDTO> getAll() {
        return modelRepository.findAll()
            .stream()
            .map(modelMapper::toDTO)
            .toList();
    }

    @Override
    public List<ModelDTO> findById(Long id) {
        return null;
    }

    @Override
    protected List<ModelDTO> deleteById(Long id) {
        return null;
    }

    @Override
    protected List<ModelDTO> update() {
        return null;
    }

    @Transactional(readOnly = true)
    public Set<ModelDTO> getAllDistinctByBrandName(String brandName) {
        return modelRepository.findAllByBrandName(brandName)
            .stream()
            .map(modelMapper::toDTO)
            .collect(Collectors.toSet());
    }
}
