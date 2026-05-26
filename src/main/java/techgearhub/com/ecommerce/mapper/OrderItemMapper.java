package techgearhub.com.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import techgearhub.com.ecommerce.dto.OrderItemDTO;
import techgearhub.com.ecommerce.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    // Da Entità a Record DTO
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemDTO toDTO(OrderItem orderItem);

    // Da Record DTO a Entità
    @Mapping(source = "productId", target = "product.id")
    OrderItem toEntity(OrderItemDTO orderItemDTO);
}
