package tu.carshop.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import tu.carshop.dtos.UserProfileDTO;
import tu.carshop.dtos.UserRegisterDTO;
import tu.carshop.models.User;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "imageURL", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    User toEntity(UserRegisterDTO source);

    @AfterMapping
    default void setTimestamps(UserRegisterDTO userRegisterDTO, @MappingTarget User user) {
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
    }

    UserProfileDTO toDTO(User source);
}
