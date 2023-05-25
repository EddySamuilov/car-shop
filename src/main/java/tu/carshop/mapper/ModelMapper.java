package tu.carshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tu.carshop.dtos.ModelDTO;
import tu.carshop.models.Model;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ModelMapper {

    ModelDTO toDTO(Model source);

}
