package mws.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mws.dao.ProductMapper;
import mws.dao.ApiMapper;
import mws.dao.UserMapper;
import mws.pojo.Activities;
import mws.pojo.Banner;
import mws.pojo.Category;
import mws.pojo.Catesitem;
import mws.pojo.Order;
import mws.pojo.OrderItem;
import mws.pojo.Product;
import mws.pojo.User;
import mws.pojo.UserAddr;
import mws.service.ApiService;
import utils.JwtTokenUtil;

@Service
public class ApiServiceImpl implements ApiService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Value("${jwt.tokenHead}")
    private String tokenHead;
	
	@Autowired
	private ApiMapper apiMapper;
	
	@Override
	public List<Banner> getBannerListWhere(Banner banner) {
		return this.apiMapper.getBannerList(banner);
	}
	
	@Override
	public List<Catesitem> getCatesitemListWhere(Catesitem catesitem) {
		return this.apiMapper.getCatesitemList(catesitem);
	}
	
	@Override
	public List<Category> getCategoryList(Category category){
		return this.apiMapper.getCategoryList(category);
	}

	@Override
	public List<Product> getProductList(Product product){
		return this.apiMapper.getProductList(product);
	}
	
	@Override
	public List<Activities> getActivitiesList(Activities activities){
		return this.apiMapper.getActivitiesList(activities);
	}
	
	@Override
	public List<UserAddr> getUserAddrList(int userId){
		return this.apiMapper.getUserAddrList(userId);
	}
	
	public User register(User userToAdd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setRole("普通用户");
        String registime="";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =new Date();
        registime = sdf.format(date);
        userToAdd.setRegistTime(registime);
        int result=this.userMapper.createUser(userToAdd);
        if(result>0){
        	User userInfo=this.userMapper.getUserByOpenid(userToAdd.getWx_openid());
            result=this.userMapper.addUserWithRole(userInfo.getId());
        }
        if (result>0) {
            return userToAdd;
        } else {
            return null;
        }
    }
	
	@Override
	public int addrDefAll0(int userId){
		return this.apiMapper.addrDefAll0(userId);
	}
	
	@Override
	public int updateAddrDef(int id){
		return this.apiMapper.updateAddrDef(id);
	}
	
	@Override
	public int deleteAddr(int id){
		return this.apiMapper.deleteAddr(id);
	}
	
	@Override
	public int insertAddr(UserAddr userAddr){
		return this.apiMapper.insertAddr(userAddr);
	}
	
	@Override
	public int updateAddr(UserAddr userAddr){
		return this.apiMapper.updateAddr(userAddr);
	}
	
	@Override
	public int insertOrder(Order order){
		return this.apiMapper.insertOrder(order);
	}
	
	@Override
	public int insertOrderitem(OrderItem orderitem){
		return this.apiMapper.insertOrderitem(orderitem);
	}

	@Override
	public List<Order> selectOrderByUserId(int userId){
		List<Order> orders=this.apiMapper.selectOrderByUserId(userId);
		for (Order order : orders) {
			String oid=order.getId();
			List<OrderItem> orderitems=this.apiMapper.selectOrderItemByOrderId(oid);
			for (OrderItem orderItem : orderitems) {
				int pid=orderItem.getProductId();
				Product product=this.productMapper.getProductById(pid);
				orderItem.setProduct(product);
			}
			order.setOrderitem(orderitems);
		}
		return orders;
	}

	@Override
	public List<Order> selectOrderByPaystate(Order order1){
		List<Order> orders=this.apiMapper.selectOrderByPaystate(order1);
		for (Order order : orders) {
			String oid=order.getId();
			List<OrderItem> orderitems=this.apiMapper.selectOrderItemByOrderId(oid);
			for (OrderItem orderItem : orderitems) {
				int pid=orderItem.getProductId();
				Product product=this.productMapper.getProductById(pid);
				orderItem.setProduct(product);
			}
			order.setOrderitem(orderitems);
		}
		return orders;
	}

	@Override
	public List<Order> selectOrderByOrderState(Order order2){
		List<Order> orders=this.apiMapper.selectOrderByOrderState(order2);
		for (Order order : orders) {
			String oid=order.getId();
			List<OrderItem> orderitems=this.apiMapper.selectOrderItemByOrderId(oid);
			for (OrderItem orderItem : orderitems) {
				int pid=orderItem.getProductId();
				Product product=this.productMapper.getProductById(pid);
				orderItem.setProduct(product);
			}
			order.setOrderitem(orderitems);
		}
		return orders;
	}

	@Override
	public List<OrderItem> selectOrderItemByOrderId(String orderId){
		return this.apiMapper.selectOrderItemByOrderId(orderId);
	}
	
	@Override
	public int orderPay(String id){
		return this.apiMapper.orderPay(id);
	}
	
	@Override
	public int orderCancel(String id){
		return this.apiMapper.orderCancel(id);
	}
	
	@Override
	public int orderFinish(String id){
		return this.apiMapper.orderFinish(id);
	}
	
	@Override
	public int orderRemove(String id){
		return this.apiMapper.orderRemove(id);
	}
}
