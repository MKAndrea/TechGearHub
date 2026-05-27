package techgearhub.com.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import techgearhub.com.ecommerce.dto.CartItemDTO;
import techgearhub.com.ecommerce.model.CartItem;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CartItemMapper {

    CartItemDTO toDTO(CartItem cartItem);

    @Mapping(target = "cart", ignore = true) // Ignoriamo il carrello ciclico per evitare loop infiniti
    CartItem toEntity(CartItemDTO cartItemDTO);
}