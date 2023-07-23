package tu.carshop.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import tu.carshop.dtos.CreateOfferDTO;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.models.Offer;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OfferMapper {

    @Mapping(target = "model", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "posts", ignore = true)
    Offer toEntity(CreateOfferDTO source);

    @Mapping(target = "model", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "posts", ignore = true)
    Offer toEntity(OfferDTO source);

    @Mapping(target = "model", source = "source.model.name")
    @Mapping(target = "brand", source = "source.model.brand.name")
    @Mapping(target = "created", source = "source.created", qualifiedByName = "toDate")
    @Mapping(target = "modified", source = "source.modified", qualifiedByName = "toDate")
    OfferDTO toDTO(Offer source);

    @Named("toDate")
    default LocalDate toDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }
}
