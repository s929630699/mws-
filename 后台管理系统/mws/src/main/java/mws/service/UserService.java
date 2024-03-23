package mws.service;

import java.util.List;

import mws.pojo.User;
import mws.pojo.UserRole;


public interface UserService {
	public List<User> getUserListWhere(User user);
	
	public User getUserById(int id);
	
	public User getOneUserByUsername(String username);
	
	public List<User> getUserByUsername(String username);
	
	public List<User> getUserByTelephone(String telephone);
	
	public int insertUser(User user);
	
	public int insertUserRole(UserRole userRole);
	
	public int updateUser(User user);
	
	public int deleteUser(int id);
	
	public int deleteUserRole(int userId);
	
	public User getUserByOpenid(String openid);
	
	public int createUser(User userToAdd);

	public int addUserWithRole(int userId);

}
