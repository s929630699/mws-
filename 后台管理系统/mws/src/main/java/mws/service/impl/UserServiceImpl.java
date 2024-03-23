package mws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mws.dao.UserMapper;
import mws.pojo.User;
import mws.pojo.UserRole;
import mws.service.UserService;

@Service
public class UserServiceImpl implements  UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getUserListWhere(User user) {
		return this.userMapper.getUserList(user);
	}
	
	@Override
	public User getUserById(int id){
		return this.userMapper.getUserById(id);
	}
	
	@Override
	public User getOneUserByUsername(String username){
		return this.userMapper.getOneUserByUsername(username);
	}
	@Override
	public List<User> getUserByUsername(String username){
		return this.userMapper.getUserByUsername(username);
	}
	
	@Override
	public List<User> getUserByTelephone(String telephone){
		return this.userMapper.getUserByTelephone(telephone);
	}
	
	@Override
	public int insertUser(User user){
		return this.userMapper.insertUser(user);
	}
	
	@Override
	public int insertUserRole(UserRole userRole){
		return this.userMapper.insertUserRole(userRole);
	}
	
	@Override
	public int updateUser(User user){
		return this.userMapper.updateUser(user);
	}
	
	@Override
	public int deleteUser(int id){
		return this.userMapper.deleteUser(id);
	}
	
	@Override
	public int deleteUserRole(int userId){
		return this.userMapper.deleteUserRole(userId);
	}
	
	@Override
	public User getUserByOpenid(String openid){
		return this.userMapper.getUserByOpenid(openid);
	}
	
	@Override
	public int createUser(User userToAdd){
		return this.userMapper.createUser(userToAdd);
	}
	
	@Override
	public int addUserWithRole(int userId){
		return this.userMapper.addUserWithRole(userId);
	}

}
