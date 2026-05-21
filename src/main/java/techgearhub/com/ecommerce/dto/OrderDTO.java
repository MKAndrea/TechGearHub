package techgearhub.com.ecommerce.dto;

import techgearhub.com.ecommerce.model.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
    Long id,
    LocalDateTime orderDate,
    OrderStatus status,
    Double total,
    Long userId,
    String userName,
    List<OrderItemDTO> items
) {}
