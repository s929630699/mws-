package mws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mws.dao.OrderMapper;
import mws.pojo.Order;
import mws.pojo.OrderItem;
import mws.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public List<Order> getOrderListWhere(Order order) {
		return this.orderMapper.getOrderList(order);
	}
	
	@Override
	public Order getOrderById(String id){
		return this.orderMapper.getOrderById(id);
	}
	
	@Override
	public List<OrderItem> getOrderItemById(String orderId){
		return this.orderMapper.getOrderItemById(orderId);
	}
	
	@Override
	public List<Order> getOrderByUserId(int userId){
		return this.orderMapper.getOrderByUserId(userId);
	}
	
	@Override
	public int insertOrder(Order order){
		return this.orderMapper.insertOrder(order);
	}
	
	@Override
	public int updateOrder(Order order){
		return this.orderMapper.updateOrder(order);
	}
	
	@Override
	public int deleteOrder(String id){
		return this.orderMapper.deleteOrder(id);
	}
	

}
