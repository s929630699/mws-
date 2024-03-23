package mws.pojo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order {
	private String id;
	private Double money;
	private String receiverAddress;
	private String receiverName;
	private String receiverPhone;
	private String paystate;
	private String ordertime;
	private int user_id;
	private String orderState;
	private String remarks;
	private List<OrderItem> orderitem;
	public List<OrderItem> getOrderitem() {
		return orderitem;
	}
	public void setOrderitem(List<OrderItem> orderitem) {
		this.orderitem = orderitem;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getPaystate() {
		return paystate;
	}
	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
	public String getOrdertime() {
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date=null;
//		try {
//			date = sdf.parse(ordertime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
//		String otime="";
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		otime=sdf.format(ordertime);
		this.ordertime = ordertime;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Order(String id, Double money, String receiverAddress, String receiverName, String receiverPhone,
			String paystate, String ordertime, int user_id, String orderState, String remarks,
			List<OrderItem> orderitem) {
		super();
		this.id = id;
		this.money = money;
		this.receiverAddress = receiverAddress;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.paystate = paystate;
		this.ordertime = ordertime;
		this.user_id = user_id;
		this.orderState = orderState;
		this.remarks = remarks;
		this.orderitem = orderitem;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", money=" + money + ", receiverAddress=" + receiverAddress + ", receiverName="
				+ receiverName + ", receiverPhone=" + receiverPhone + ", paystate=" + paystate + ", ordertime="
				+ ordertime + ", user_id=" + user_id + ", orderState=" + orderState + ", remarks=" + remarks
				+ ", orderitem=" + orderitem + "]";
	}
	
	

}
