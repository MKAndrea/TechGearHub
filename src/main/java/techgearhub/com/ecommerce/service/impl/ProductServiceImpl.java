package techgearhub.com.ecommerce.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import techgearhub.com.ecommerce.dto.ProductDTO;
import techgearhub.com.ecommerce.mapper.ProductMapper;
import techgearhub.com.ecommerce.model.Product;
import techgearhub.com.ecommerce.repository.ProductRepository;
import techgearhub.com.ecommerce.service.ProductService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    @Override
    public List<ProductDTO> allProducts() {
        return productRepository.findAllByActiveTrue().stream()
                .map(productMapper::toDTO) // <-- Pulito, elegante e immediato!
                .toList();
    }
	
    @Override
    public ProductDTO findById(Long id) {
        return productRepository.findByIdAndActiveTrue(id)
                .map(productMapper::toDTO) // <-- Usa il mapper automatico
                .orElse(null);
    }
    @Override
    public List<ProductDTO> deactivatedProducts() {
        return productRepository.findAllByActiveFalse().stream()
                .map(productMapper::toDTO)
                .toList();
    }
    
    @Override
    public ProductDTO createNewProduct(ProductDTO prodottoDto) {
        var productEntity = productMapper.toEntity(prodottoDto);
        var savedProduct = productRepository.save(productEntity);
        
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findByIdAndActiveTrue(id).ifPresent(product -> {
            product.setActive(false);
            productRepository.save(product);
        });
    }
    
    public void restoreProduct(Long id) {
        productRepository.findById(id).ifPresent(product -> {
            if (!product.isActive()) {
                product.setActive(true);
                productRepository.save(product);
            }
        });
    }
}