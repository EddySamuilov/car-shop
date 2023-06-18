package tu.carshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tu.carshop.dtos.BrandDTO;
import tu.carshop.dtos.CreateBrandDTO;
import tu.carshop.models.Brand;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {

    BrandDTO toDTO(Brand source);

    Brand toEntity(CreateBrandDTO source);
}
