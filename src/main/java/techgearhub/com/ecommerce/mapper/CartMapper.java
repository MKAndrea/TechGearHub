package techgearhub.com.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import techgearhub.com.ecommerce.dto.CartDTO;
import techgearhub.com.ecommerce.model.Cart;

@Mapper(componentModel = "spring", uses = {CartItemMapper.class})
public interface CartMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "totalPrice", target = "totalPrice") 
    CartDTO toDTO(Cart cart);

    @Mapping(target = "user", ignore = true)
    Cart toEntity(CartDTO cartDTO);
}
