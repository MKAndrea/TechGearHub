package techgearhub.com.ecommerce.mapper;

import org.mapstruct.Mapper;
import techgearhub.com.ecommerce.dto.CategoryDTO;
import techgearhub.com.ecommerce.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
