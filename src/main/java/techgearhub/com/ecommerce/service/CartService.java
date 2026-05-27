package techgearhub.com.ecommerce.service;

import java.util.List;

import techgearhub.com.ecommerce.dto.CartDTO;

public interface CartService {
	List<CartDTO> getAllCarts();
    CartDTO getCartByUserId(Long userId);
    CartDTO addProductToCart(Long userId, Long productId, Integer quantity);
    CartDTO removeProductFromCart(Long userId, Long productId);
    void clearCart(Long userId);
}