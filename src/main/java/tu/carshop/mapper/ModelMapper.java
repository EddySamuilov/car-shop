package tu.carshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tu.carshop.dtos.ModelDTO;
import tu.carshop.models.Model;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModelMapper {

    ModelDTO toDTO(Model source);

}
