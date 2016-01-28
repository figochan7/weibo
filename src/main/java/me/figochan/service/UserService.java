package me.figochan.service;

import java.util.List;

import me.figochan.entities.User;

public interface UserService {
	public String saveUser(User user);
	public String updateUser(User user);
	public User getUserById(String id);
	public User getUserByUsername(String username);
	public String addWeiboCount(User user);
	public String addFansCount(User user);
	public String addFollowCount(User user);
	public String addMessageCount(User user);
	
	public String minusWeiboCount(User user);
	public String minusFansCount(User user);
	public String minusFollowCount(User user);
	public String minusMessageCount(User user);
	
	public List<User> getHotUser();
	
	public String updatePassword(User user,String password);

}
