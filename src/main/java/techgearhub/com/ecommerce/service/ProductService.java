package techgearhub.com.ecommerce.service;

import java.util.List;

import techgearhub.com.ecommerce.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> allProducts();
    
    List<ProductDTO> deactivatedProducts();
    
    ProductDTO findById(Long id);
    
    ProductDTO createNewProduct(ProductDTO prodotto);
    
    void deleteProduct(Long id);

}