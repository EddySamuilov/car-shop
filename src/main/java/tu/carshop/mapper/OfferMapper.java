package tu.carshop.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.models.Offer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OfferMapper {

    @Mapping(target = "model", source = "source.model.name")
    @Mapping(target = "brand", source = "source.model.brand.name")
    OfferDTO toDTO(Offer source);
}
