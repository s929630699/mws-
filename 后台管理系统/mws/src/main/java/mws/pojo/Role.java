package mws.pojo;

public class Role {
	private int id;
	private String roleName;
	private String roleDesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + "]";
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(int id, String roleName, String roleDesc) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
	}
	

}
