package techgearhub.com.ecommerce.dto;

public record CartItemDTO(
    Long id,
    ProductDTO product, // Inviamo il DTO completo del prodotto per mostrare dettagli nel frontend
    Integer quantity
) {}