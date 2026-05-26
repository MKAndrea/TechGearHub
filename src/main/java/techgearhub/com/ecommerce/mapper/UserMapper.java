package techgearhub.com.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import techgearhub.com.ecommerce.dto.UserDTO;
import techgearhub.com.ecommerce.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);
}
