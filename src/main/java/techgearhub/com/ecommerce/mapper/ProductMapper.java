package techgearhub.com.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import techgearhub.com.ecommerce.dto.ProductDTO;
import techgearhub.com.ecommerce.model.Product;

@Mapper(componentModel = "spring") // Dice a Spring di gestire questo Mapper come un Bean (@Component)
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")    // Prendi l'id della categoria e mettilo in categoryId
    @Mapping(source = "category.name", target = "categoryName") // Prendi il nome della categoria e mettilo in categoryName
    ProductDTO toDTO(Product product);

    @Mapping(source = "categoryId", target = "category.id")     // Fai il percorso inverso
    Product toEntity(ProductDTO productDTO);
}
