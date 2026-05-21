package techgearhub.com.ecommerce.dto;

public record ProductDTO(
    Long id,
    String name,
    String description,
    Double price,
    String imageUrl,
    Integer stock,
    Long categoryId,    
    String categoryName
) {}