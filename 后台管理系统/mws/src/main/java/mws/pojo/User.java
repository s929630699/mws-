package mws.pojo;

import java.util.Date;

public class User {
	private Integer id;
	private String username;
	private String password;
	private String gender;
	private String telephone;
	private String introduce;
	private String role;
	private String registTime;
	private String token;
	private String wx_openid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getWx_openid() {
		return wx_openid;
	}
	public void setWx_openid(String wx_openid) {
		this.wx_openid = wx_openid;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", telephone=" + telephone + ", introduce=" + introduce + ", role=" + role + ", registTime="
				+ registTime + ", token=" + token + ", wx_openid=" + wx_openid + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String username, String password, String gender, String telephone, String introduce,
			String role, String registTime, String token, String wx_openid) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.telephone = telephone;
		this.introduce = introduce;
		this.role = role;
		this.registTime = registTime;
		this.token = token;
		this.wx_openid = wx_openid;
	}
	
	

}
