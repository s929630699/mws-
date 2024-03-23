package mws.pojo;

public class UserRole {
	private int id;
	private int userId;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRole(int id, int userId, int roleId) {
		super();
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", roleId=" + roleId + "]";
	}
	
	

}
