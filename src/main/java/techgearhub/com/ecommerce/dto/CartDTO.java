package techgearhub.com.ecommerce.dto;

import java.util.List;

public record CartDTO(
    Long id,
    Long userId,
    List<CartItemDTO> items,
    Double totalPrice
) {}