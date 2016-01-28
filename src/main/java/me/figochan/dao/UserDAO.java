package me.figochan.dao;

import java.util.List;

import me.figochan.entities.User;

public interface UserDAO {
	public boolean saveUser(User user);
	public User getUserById(String id);
	public User getUserByUsername(String username);
	public boolean updateUser(User user);
	public boolean addWeiboCount(User user);
	public boolean addFansCount(User user);
	public boolean addFollowCount(User user);
	public boolean addMessageCount(User user);
	public boolean minusWeiboCount(User user);
	public boolean minusFansCount(User user);
	public boolean minusFollowCount(User user);
	public boolean minusMessageCount(User user);
	public List<User> getHotUser();
	
	public boolean updatePassword(User user, String password);
}
