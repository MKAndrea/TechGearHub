package techgearhub.com.ecommerce.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import techgearhub.com.ecommerce.dto.OrderDTO;
import techgearhub.com.ecommerce.mapper.OrderMapper;
import techgearhub.com.ecommerce.model.Order;
import techgearhub.com.ecommerce.model.OrderStatus;
import techgearhub.com.ecommerce.repository.OrderRepository;
import techgearhub.com.ecommerce.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    
    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
        		.filter(order -> order.getStatus()!=OrderStatus.CANCELLED)
                .map(orderMapper::toDTO)
                .toList();
    }
    @Override
    public List<OrderDTO> getAllOrdersCancelled() {
        return orderRepository.findAll().stream()
        		.filter(order -> order.getStatus()==OrderStatus.CANCELLED)
                .map(orderMapper::toDTO)
                .toList();
    }
    
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        
        Order savedOrder = orderRepository.save(order);
      
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato con ID: " + id));
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .filter(order -> order.getStatus() != OrderStatus.CANCELLED)
                .map(orderMapper::toDTO)
                .toList();
    }

    @Override
    public List<OrderDTO> getCancelledOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .filter(order -> order.getStatus() == OrderStatus.CANCELLED)
                .map(orderMapper::toDTO)
                .toList();
    }

    @Override
    public OrderDTO updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossibile aggiornare: Ordine non trovato con ID: " + id));
        
        order.setStatus(status);
        return orderMapper.toDTO(orderRepository.save(order));
    }
    
    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossibile annullare: Ordine non trovato con ID: " + id));
        
        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Impossibile annullare un ordine già spedito o consegnato.");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}