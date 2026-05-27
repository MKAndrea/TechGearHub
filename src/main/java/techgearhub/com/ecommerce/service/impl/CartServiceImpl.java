package techgearhub.com.ecommerce.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import techgearhub.com.ecommerce.dto.CartDTO;
import techgearhub.com.ecommerce.mapper.CartMapper;
import techgearhub.com.ecommerce.model.Cart;
import techgearhub.com.ecommerce.model.CartItem;
import techgearhub.com.ecommerce.model.Product;
import techgearhub.com.ecommerce.model.User;
import techgearhub.com.ecommerce.repository.CartRepository;
import techgearhub.com.ecommerce.repository.ProductRepository;
import techgearhub.com.ecommerce.repository.UserRepository;
import techgearhub.com.ecommerce.service.CartService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional 
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    
    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(cartMapper::toDTO)
                .toList();
    }
    
    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = getOrCreateCart(userId);
        return cartMapper.toDTO(cart);
    }

    @Override
    public CartDTO addProductToCart(Long userId, Long productId, Integer quantity) {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con ID: " + productId));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }

    @Override
    public CartDTO removeProductFromCart(Long userId, Long productId) {
        Cart cart = getOrCreateCart(userId);

        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Prodotto non presente nel carrello."));

        cart.getItems().remove(itemToRemove);

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrello non trovato per l'utente con ID: " + userId));
        
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    private Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + userId));
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }
}