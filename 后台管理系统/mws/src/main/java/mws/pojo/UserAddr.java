package mws.pojo;

public class UserAddr {
	private Integer id;
	private Integer userId;
	private String receiver;
	private String mobile;
	private String address;
	private Integer defaultState; 
	User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getDefaultState() {
		return defaultState;
	}
	public void setDefaultState(Integer defaultState) {
		this.defaultState = defaultState;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserAddr() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserAddr [id=" + id + ", userId=" + userId + ", receiver=" + receiver + ", mobile=" + mobile
				+ ", address=" + address + ", defaultState=" + defaultState + ", user=" + user + "]";
	}
	public UserAddr(Integer id, Integer userId, String receiver, String mobile, String address, Integer defaultState,
			User user) {
		super();
		this.id = id;
		this.userId = userId;
		this.receiver = receiver;
		this.mobile = mobile;
		this.address = address;
		this.defaultState = defaultState;
		this.user = user;
	}
	

}
