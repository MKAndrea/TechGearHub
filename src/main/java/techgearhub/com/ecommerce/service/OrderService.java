package techgearhub.com.ecommerce.service;

import java.util.List;
import techgearhub.com.ecommerce.dto.OrderDTO;
import techgearhub.com.ecommerce.model.OrderStatus;

public interface OrderService {
	List<OrderDTO> getAllOrders();
	List<OrderDTO> getAllOrdersCancelled();
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersByUserId(Long userId);
    List<OrderDTO> getCancelledOrdersByUserId(Long userId);
    OrderDTO updateStatus(Long id, OrderStatus status);
    void cancelOrder(Long id);
}