package tu.carshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tu.carshop.dtos.BrandDTO;
import tu.carshop.dtos.CreateBrandDTO;
import tu.carshop.dtos.ModelDTO;
import tu.carshop.mapper.BrandMapper;
import tu.carshop.models.Brand;
import tu.carshop.repositories.BrandRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService extends BaseService<BrandDTO> {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final ModelService modelService;

    @Transactional(readOnly = true)
    @Override
    public List<BrandDTO> getAll() {
        List<Brand> brands = brandRepository.findAll();

        List<BrandDTO> result = new ArrayList<>();
        brands.forEach(brand -> {
            Set<ModelDTO> models = modelService.getAllDistinctByBrandName(brand.getName());

            result.add(new BrandDTO(brand.getName(), models));
        });

        return result;
    }

    @Override
    public BrandDTO findById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public BrandDTO update(Long id, BrandDTO updateDto) {
        return null;
    }

    public BrandDTO create(CreateBrandDTO createBrandDTO) {
        Brand brand = brandMapper.toEntity(createBrandDTO);
        brand.setCreated(LocalDateTime.now());
        brand.setModified(LocalDateTime.now());

        return brandMapper.toDTO(brandRepository.save(brand));
    }
}