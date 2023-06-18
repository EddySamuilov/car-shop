package tu.carshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tu.carshop.dtos.CreateModelDTO;
import tu.carshop.dtos.ModelDTO;
import tu.carshop.exceptions.ObjectNotFoundException;
import tu.carshop.mapper.ModelMapper;
import tu.carshop.models.Brand;
import tu.carshop.models.Model;
import tu.carshop.repositories.ModelRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ModelService extends BaseService<ModelDTO> {
    private static final String MODEL_NOT_FOUND_ERROR_MESSAGE = "Model with id %s not found!";

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    @Override
    @Transactional(readOnly = true)
    public List<ModelDTO> getAll() {
        return modelRepository.findAll()
            .stream()
            .map(modelMapper::toDTO)
            .toList();
    }

    @Override
    public ModelDTO findById(Long id) {
        return modelRepository.findById(id)
                .map(modelMapper::toDTO)
                .orElseThrow(() -> new ObjectNotFoundException(String.format(MODEL_NOT_FOUND_ERROR_MESSAGE, id)));
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public ModelDTO update(Long id, ModelDTO updateDto) {
        return null;
    }

    @Transactional(readOnly = true)
    public Set<ModelDTO> getAllDistinctByBrandName(String brandName) {
        return modelRepository.findAllByBrandName(brandName)
            .stream()
            .map(modelMapper::toDTO)
            .collect(Collectors.toSet());
    }

    public void create(CreateModelDTO createModelDTO) {
        Brand brand = brandService.getBrandByName(createModelDTO.getBrandName());

        Model model = modelMapper.toEntity(createModelDTO);
        model.setBrand(brand);

        modelRepository.save(model);
    }
}
