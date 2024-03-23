package mws.dao;

import java.util.List;

import mws.pojo.Order;
import mws.pojo.OrderItem;


public interface OrderMapper {
	public List<Order> getOrderList(Order order);
	
	public Order getOrderById(String id);
	
	public List<OrderItem> getOrderItemById(String orderId);
	
	public List<Order> getOrderByUserId(int userId);
	
	public int insertOrder(Order order);
	
	public int updateOrder(Order order);
	
	public int deleteOrder(String id);

}
