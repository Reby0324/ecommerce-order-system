package service;

import java.util.List;

import entity.Order_Items;
import entity.Orders;

public interface OrdersService {
	    
		int createOrder(Orders orders);
		boolean createOrderItem(Order_Items orderItems);

		
		List<Orders> findAll();
		List<Orders> findById(Integer id);
		List<Orders> findByUserId(Integer userId);

		List<Order_Items> findItemsByOrderId(Integer orderId);

		
		boolean updateStatus(Integer id, String status);

		
		boolean deleteById(Integer id);
}
