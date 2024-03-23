package mws.pojo;

public class OrderItem {
	private int id;
	private String orderId;
	private Integer productId;
	private String productName;
	private int buyNum;
	private float buyPrice;
	private Product product;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public float getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", productName="
				+ productName + ", buyNum=" + buyNum + ", buyPrice=" + buyPrice + ", product=" + product + "]";
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItem(int id, String orderId, Integer productId, String productName, int buyNum, float buyPrice,
			Product product) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.buyNum = buyNum;
		this.buyPrice = buyPrice;
		this.product = product;
	}
	
	
	
	
	

}
