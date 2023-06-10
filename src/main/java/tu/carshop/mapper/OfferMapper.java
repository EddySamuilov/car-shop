package tu.carshop.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.models.Offer;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OfferMapper {

    OfferDTO toDTO(Offer source);
}
