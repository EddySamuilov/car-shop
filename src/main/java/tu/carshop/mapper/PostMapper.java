package tu.carshop.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import tu.carshop.dtos.PostDTO;
import tu.carshop.models.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PostMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "author", ignore = true)
  @Mapping(target = "offer", ignore = true)
  Post toEntity(PostDTO source);

  @Mapping(target = "offerId", ignore = true)
  @Mapping(target = "created", source = "source.created", qualifiedByName = "toDate")
  @Mapping(target = "modified", source = "source.modified", qualifiedByName = "toDate")
  PostDTO toDTO(Post source);

  @Named("toDate")
  default LocalDate toDate(LocalDateTime localDateTime) {
    return localDateTime.toLocalDate();
  }
}
