package techgearhub.com.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import techgearhub.com.ecommerce.dto.OrderDTO;
import techgearhub.com.ecommerce.model.Order;

@Mapper(componentModel = "spring", uses = { OrderItemMapper.class })
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderDTO toDTO(Order order);


    @Mapping(source = "userId", target = "user.id")
    Order toEntity(OrderDTO orderDTO);
}