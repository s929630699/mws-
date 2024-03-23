package mws.service;

import java.util.List;

import mws.pojo.Activities;
import mws.pojo.Banner;
import mws.pojo.Category;
import mws.pojo.Catesitem;
import mws.pojo.Order;
import mws.pojo.OrderItem;
import mws.pojo.Product;
import mws.pojo.User;
import mws.pojo.UserAddr;

public interface ApiService {
	public List<Banner> getBannerListWhere(Banner banner);
	
	public List<Catesitem> getCatesitemListWhere(Catesitem catesitem);
	
	public List<Category> getCategoryList(Category category);

	public List<Product> getProductList(Product product);
	
	public List<Activities> getActivitiesList(Activities activities);
	
	public List<UserAddr> getUserAddrList(int userId);
	
	public User register(User userToAdd);
	
	public int addrDefAll0(int userId);
	
	public int updateAddrDef(int id);
	
	public int deleteAddr(int id);
	
	public int insertAddr(UserAddr userAddr);
	
	public int updateAddr(UserAddr userAddr);
	
	public int insertOrder(Order order);
	
	public int insertOrderitem(OrderItem orderitem);
	
	public List<Order> selectOrderByUserId(int userId);
	
	public List<Order> selectOrderByPaystate(Order order);
	
	public List<Order> selectOrderByOrderState(Order order);
	
	public List<OrderItem> selectOrderItemByOrderId(String orderId);
	
	public int orderPay(String id);
	
	public int orderCancel(String id);
	
	public int orderFinish(String id);
	
	public int orderRemove(String id);
	
}
