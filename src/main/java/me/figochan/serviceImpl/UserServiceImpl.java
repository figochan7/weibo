package me.figochan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.figochan.dao.UserDAO;
import me.figochan.entities.User;
import me.figochan.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public String saveUser(User user) {
		try{
			User temp = userDAO.getUserByUsername(user.getUsername());
			if(temp!=null){
				return "用户名已存在!";
			}
			boolean result = userDAO.saveUser(user);
			if(result){
				return "success";
			}
			return "添加失败！请稍后再试！";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Override
	@Transactional
	public String updateUser(User user) {
		try{
			boolean result = this.userDAO.updateUser(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Override
	public User getUserById(String id) {
		try{
			User user = this.userDAO.getUserById(id);
			return user;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		try{
			User user = this.userDAO.getUserByUsername(username);
			return user;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public String addWeiboCount(User user) {
		try{
			boolean result = this.userDAO.addWeiboCount(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Transactional
	@Override
	public String addFansCount(User user) {
		try{
			boolean result = this.userDAO.addFansCount(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Transactional
	@Override
	public String addFollowCount(User user) {
		try{
			boolean result = this.userDAO.addFollowCount(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Transactional
	@Override
	public String addMessageCount(User user) {
		try{
			boolean result = this.userDAO.addMessageCount(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Override
	public List<User> getHotUser() {
		return this.userDAO.getHotUser();
	}

	@Transactional
	@Override
	public String updatePassword(User user, String password) {
		boolean result=this.userDAO.updatePassword(user, password);
		if(result){
			return "success";
		}
		return "更新密码失败";
	}

	@Transactional
	@Override
	public String minusWeiboCount(User user) {
		try{
			boolean result = this.userDAO.minusWeiboCount(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Transactional
	@Override
	public String minusFansCount(User user) {
		try{
			boolean result = this.userDAO.minusFansCount(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Transactional
	@Override
	public String minusFollowCount(User user) {
		try{
			boolean result = this.userDAO.minusFollowCount(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

	@Transactional
	@Override
	public String minusMessageCount(User user) {
		try{
			boolean result = this.userDAO.minusMessageCount(user);
			if(!result){
				return "该用户不存在！";
			}
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "系统故障，请稍后再试！";
		}
	}

}
