package techgearhub.com.ecommerce.dto;

public record OrderItemDTO(
    Long id,
    Long productId,
    String productName,
    Integer quantity,
    Double priceAtPurchase
) {}
