package techgearhub.com.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import techgearhub.com.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByActiveTrue();
    
    List<Product> findAllByActiveFalse();
    
    Optional<Product> findByIdAndActiveTrue(Long id);
}
