package me.figochan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import me.figochan.dao.GroupDAO;
import me.figochan.entities.Group;
import me.figochan.entities.User;
import me.figochan.service.GroupService;

@Component
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDAO;
	
	@Transactional
	@Override
	public String addGroup(Group group) {
		boolean result = groupDAO.addGroup(group);
		if(result){
			return "success";
		}
		return "添加失败！请稍后再试！";
	}

	@Override
	public Group getGroupById(String id) {
		try{
			Group group = this.groupDAO.getGroupById(id);
			return group;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Group getGroupByName(String name) {
		try{
			Group group = this.groupDAO.getGroupByName(name);
			return group;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Group> getGroupByUser(String userid) {
		return this.groupDAO.getGroupByUser(userid);
	}

	@Override
	public String deleteGroup(Group group) {
		boolean result= this.groupDAO.deleteGroup(group);
		if(result){
			return "success";
		}else{
			return "删除分组失败";
		}
	}

	@Override
	public Group getGroupByUserAndName(User user, String name) {
		try{
			Group group = this.groupDAO.getGroupByUserANdName(user, name);
			return group;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
